package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.OutstoreDetailsEntity;
import edu.cqun.warehouse.entity.OutstoreSheetEntity;
import edu.cqun.warehouse.entity.StoreAmountEntity;
import edu.cqun.warehouse.entity.UserEntity;
import edu.cqun.warehouse.service.IOutstoreService;
import edu.cqun.warehouse.service.IStoreAmountService;
import edu.cqun.warehouse.service.IUserService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2015/4/29.
 */
@Controller
public class ModifyOutstoreSheetAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IOutstoreService outstoreService;

    @Resource
    private IUserService userService;

    @Resource
    private IStoreAmountService storeAmountService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String department;
    private String dataString;
    private String username;
    private String outstoreSheetId;

    @Override
    public String execute(){
        try{
            //更新出库单的修改信息 包括修改时间和修改人
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            UserEntity user = userService.findByName(username);
            String realName = user.getUserRealName();
            Integer outsId = Integer.parseInt(outstoreSheetId);
            OutstoreSheetEntity outstoreSheet = outstoreService.findOutstoreSheetById(outsId);
            outstoreSheet.setModifier(realName);
            outstoreSheet.setModifyDateAt(timestamp);
            outstoreService.update(outstoreSheet);

            //获得当前出库单年月 供库存信息表查询用
            Timestamp timestamp2 = outstoreSheet.getDateAt();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(timestamp2);
            Integer year = calendar.get(Calendar.YEAR);
            Integer month = calendar.get(Calendar.MONTH)+1;
            Integer monthAt = year * 100 + month;

            //获得修改时间年月 供库存信息表查询用
            Calendar calendarNow = Calendar.getInstance();
            Integer yearNow = calendarNow.get(Calendar.YEAR);
            Integer monthNow = calendarNow.get(Calendar.MONTH)+1;
            Integer monthAtNow = yearNow * 100 + monthNow;

            //获得当前出库单原来的详细信息
            List<OutstoreDetailsEntity> originOutstoreDetails = outstoreService.findOutstoreDetailsById(outsId);
            //用来储存要删除的出库单详细信息
            List<OutstoreDetailsEntity> removeOutstoreDetails = new ArrayList<OutstoreDetailsEntity>();

            //对数据流字符串进行处理
            String string[] = dataString.split(",");
            for(int i = 3;i < string.length;i += 3){
                Integer proId = Integer.parseInt(string[i]);
                Integer amount = Integer.parseInt(string[i+1]);
                OutstoreDetailsEntity outstoreDetail = outstoreService.findOutstoreDetailsByPK(outsId, proId);

                //用于获得被删除的出库单详细信息
                for(OutstoreDetailsEntity outsd : originOutstoreDetails){
                    if(outsd.getProductId() == proId)
                        removeOutstoreDetails.add(outsd);
                }

                StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt, proId);
                StoreAmountEntity storeAmountNow = storeAmountService.findByPK(monthAtNow, proId);
//                Integer rest = storeAmount.getThisMonthAmount();

                //outstoreDetail 为null说明原来的出库单没有这个材料 因此需要添加这个材料并对库存表进行出库操作（也就是库存数量减掉出库数量）
                if(outstoreDetail == null){
                    Integer originAmount;
                    if ( storeAmount != null) {//这个月这个product有库存信息
                        Integer rest = storeAmount.getThisMonthAmount();
                        originAmount = rest;
                        storeAmount.setThisMonthAmount(rest - amount);
                        storeAmountService.update(storeAmount);
                    }else{//这个product在这个月无库存信息
                        List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAt,proId);
//                        Integer lastTime = storeAmountService.findMaxMonthAt(proId);
//                        if(earlierStoreAmounts != null && !earlierStoreAmounts.isEmpty()){//这个product曾经有过库存信息
                        StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                        Integer earlierAmount = earlierStoreAmounts.get(0).getThisMonthAmount();
                        originAmount = earlierAmount;
                        newStoreAmount.setThisMonthAmount(amount + earlierAmount);
//                            StoreAmountEntity lastStoreAmount = storeAmountService.findByPK(lastTime, proId);
//                            newStoreAmount.setThisMonthAmount(lastStoreAmount.getThisMonthAmount() - amount);
                        newStoreAmount.setProductId(proId);
                        newStoreAmount.setRemark("");
                        newStoreAmount.setMonthAt(monthAt);
                        storeAmountService.add(newStoreAmount);
//                        }else {
                            //如果没有库存就出库。。
                            /*******************************************************/
                            /*该商品根本没有入库过 此处应添加不可出库的返回值！！！*/
                            /*******************************************************/
//                        }
                    }
                    OutstoreDetailsEntity newOutstoreDetail = new OutstoreDetailsEntity();
                    newOutstoreDetail.setOutstoreSheetId(outsId);
                    newOutstoreDetail.setProductId(proId);
                    newOutstoreDetail.setAmount(amount);
                    newOutstoreDetail.setRemark(string[i+2]);
                    outstoreService.add(newOutstoreDetail);

                    if(monthAt != monthAtNow){
                        //现在时间proId没有库存记录,继承最近一次proId的库存记录
                        if(storeAmountNow == null){
                            StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                            List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAtNow,proId);
                            Integer rest;
                            //如果最近一次的库存记录是出库单所在月份的话，为了防止重复减去amount，必须要记录originAmount，用来继承原来没有修改过的值（上面在赋值完originAmount后都对原来的值进行过修改）
                            // 否则在下面修改所有晚于出库单所在月份的库存记录的时候会出现重复减去amount的现象！！
                            if(earlierStoreAmounts.get(0).getMonthAt().equals(monthAt)){
                                rest = originAmount;
                            }else{
                                rest = earlierStoreAmounts.get(0).getThisMonthAmount();
                            }
                            newStoreAmount.setThisMonthAmount(rest);
                            newStoreAmount.setMonthAt(monthAtNow);
                            newStoreAmount.setProductId(proId);
                            newStoreAmount.setRemark("");
                            storeAmountService.add(newStoreAmount);
                        }
                        //查找所有晚于出库单所在月份的proId的库存记录，并修改后面的所有库存记录
                        List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt,proId);
                        for(StoreAmountEntity storeA : laterStoreAmounts){
                            Integer rest = storeA.getThisMonthAmount();
                            storeA.setThisMonthAmount(rest - amount);
                            storeAmountService.update(storeA);
                        }
                    }

                }else { //不为null说明有这个材料 所以应该先把原来的出库数量加上还原库存表 再减去修改了的出库数量
                    Integer originAmount = outstoreDetail.getAmount();

                    if(monthAt != monthAtNow) {
                        //现在时间proId没有库存记录,继承最近一次proId的库存记录
                        if (storeAmountNow == null) {
                            StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                            List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAtNow, proId);
                            Integer rest = earlierStoreAmounts.get(0).getThisMonthAmount();
                            newStoreAmount.setThisMonthAmount(rest);
                            newStoreAmount.setMonthAt(monthAtNow);
                            newStoreAmount.setProductId(proId);
                            newStoreAmount.setRemark("");
                            storeAmountService.add(newStoreAmount);
                        }
                        //查找所有晚于出库单所在月份的proId的库存记录，并修改后面的所有库存记录
                        List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt, proId);
                        for (StoreAmountEntity storeA : laterStoreAmounts) {
                            Integer rest = storeA.getThisMonthAmount();
                            storeA.setThisMonthAmount(rest + originAmount - amount);
                            storeAmountService.update(storeA);
                        }
                    }

                    Integer rest = storeAmount.getThisMonthAmount();
                    Integer modifiedRest = rest + originAmount - amount;
                    storeAmount.setThisMonthAmount(modifiedRest);
                    storeAmountService.update(storeAmount);

                    outstoreDetail.setAmount(amount);
                    outstoreDetail.setRemark(string[i+2]);
                    outstoreService.update(outstoreDetail);
                }
            }

            //如果此次修改删除了某些原本有的出库材料信息 就对库存表进行相应的库存数量操作
            originOutstoreDetails.removeAll(removeOutstoreDetails);
            if(!originOutstoreDetails.isEmpty()){
                for(OutstoreDetailsEntity outsd : originOutstoreDetails){
                    Integer proId = outsd.getProductId();
                    Integer amount = outsd.getAmount();
                    StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt, proId);
                    StoreAmountEntity storeAmountNow = storeAmountService.findByPK(monthAtNow, proId);
                    if(monthAt != monthAtNow) {
                        //现在时间proId没有库存记录,继承最近一次proId的库存记录
                        if (storeAmountNow == null) {
                            StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                            List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAtNow, proId);
                            Integer rest = earlierStoreAmounts.get(0).getThisMonthAmount();
                            newStoreAmount.setThisMonthAmount(rest);
                            newStoreAmount.setMonthAt(monthAtNow);
                            newStoreAmount.setProductId(proId);
                            newStoreAmount.setRemark("");
                            storeAmountService.add(newStoreAmount);
                        }
                        //查找所有晚于出库单所在月份的proId的库存记录，并修改后面的所有库存记录
                        List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt, proId);
                        if (laterStoreAmounts != null && !laterStoreAmounts.isEmpty()) {
                            for (StoreAmountEntity storeA : laterStoreAmounts) {
                                Integer rest = storeA.getThisMonthAmount();
                                storeA.setThisMonthAmount(rest + amount);
                                storeAmountService.update(storeA);
                            }
                        }
                    }
                    Integer rest = storeAmount.getThisMonthAmount();
                    storeAmount.setThisMonthAmount(rest + amount);
                    storeAmountService.update(storeAmount);
                    outstoreService.delete(outsd);
                }
            }
        }catch (Throwable ex){
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    @Override
    public void setServletResponse(HttpServletResponse arg0) {
        this.response = arg0;
    }

    @Override
    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    @Override
    public void setSession(Map<String, Object> arg0) {
        this.att = arg0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String getOutstoreSheetId() {
        return outstoreSheetId;
    }

    public void setOutstoreSheetId(String outstoreSheetId) {
        this.outstoreSheetId = outstoreSheetId;
    }
}

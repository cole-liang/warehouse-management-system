package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.InstoreDetailsEntity;
import edu.cqun.warehouse.entity.InstoreSheetEntity;
import edu.cqun.warehouse.entity.StoreAmountEntity;
import edu.cqun.warehouse.entity.UserEntity;
import edu.cqun.warehouse.service.IInstoreService;
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
public class ModifyInstoreSheetAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IInstoreService instoreService;

    @Resource
    private IUserService userService;

    @Resource
    private IStoreAmountService storeAmountService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String department;
    //dataString 为我自己自定义的，从页面通过ajax传回来的数据流字符串 格式为 材料ID1,入库数量1,备注1,材料ID2,入库数量2,备注2,…………
    private String dataString;
    private String username;
    private String instoreSheetId;

    @Override
    public String execute(){
        try{
            //更新入库单的修改信息 包括修改时间和修改人
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            UserEntity user = userService.findByName(username);
            String realName = user.getUserRealName();
            Integer insId = Integer.parseInt(instoreSheetId);
            InstoreSheetEntity instoreSheet = instoreService.findInstoreSheetById(insId);
            instoreSheet.setModifier(realName);
            instoreSheet.setModifyDateAt(timestamp);
            instoreService.update(instoreSheet);

            //获得当前入库单年月 供库存信息表查询用
            Timestamp timestamp2 = instoreSheet.getDateAt();
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

            //获得当前入库单原来的详细信息
            List<InstoreDetailsEntity> originInstoreDetails = instoreService.findInstoreDetailsById(insId);
            //用来储存删除的入库单详细信息
            List<InstoreDetailsEntity> removeInstoreDetails = new ArrayList<InstoreDetailsEntity>();

            //对数据流字符串进行处理
            String string[] = dataString.split(",");
            for(int i = 3;i < string.length;i += 3){
                Integer proId = Integer.parseInt(string[i]);
                Integer amount = Integer.parseInt(string[i+1]);
                InstoreDetailsEntity instoreDetail = instoreService.findInstoreDetailsByPK(insId,proId);

                //用于获得被删除的入库单详细信息
                for(InstoreDetailsEntity insd : originInstoreDetails){
                    if(insd.getProductId() == proId)
                        removeInstoreDetails.add(insd);
                }

                StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt, proId);
                StoreAmountEntity storeAmountNow = storeAmountService.findByPK(monthAtNow, proId);
//                Integer rest = storeAmount.getThisMonthAmount();

                //instoreDetail 为null说明原来的入库单没有这个材料 因此需要添加这个材料并对库存表进行入库操作（也就是库存数量加上入库数量）
                if(instoreDetail == null){
                    //记录入库单所在月份的proId原库存数量，若有则为原数量，没有设为0。作用在下面会说到。
                    Integer originAmount;
                    if ( storeAmount != null){//入库单所在月份有proId的库存记录
                        Integer rest = storeAmount.getThisMonthAmount();
                        originAmount = rest;
                        storeAmount.setThisMonthAmount(rest + amount);
                        storeAmountService.update(storeAmount);
                    }else {//入库单所在月份无proId的库存记录
                        //查找所有早于入库单所在月份的proId的库存记录
                        List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAt,proId);
//                        Integer lastTime = storeAmountService.findMaxMonthAt(proId);
                        StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                        if(earlierStoreAmounts != null && !earlierStoreAmounts.isEmpty()){
                            //earlierStoreAmounts.get(0)是proId的入库单所在月份前的最近一次库存记录
                            Integer earlierAmount = earlierStoreAmounts.get(0).getThisMonthAmount();
                            originAmount = earlierAmount;
                            newStoreAmount.setThisMonthAmount(amount + earlierAmount);
//                            StoreAmountEntity lastStoreAmount = storeAmountService.findByPK(lastTime, proId);
//                            newStoreAmount.setThisMonthAmount(amount + lastStoreAmount.getThisMonthAmount());
                        }else {
                            originAmount = 0;
                            newStoreAmount.setThisMonthAmount(amount);
                        }
//                        newStoreAmount.setThisMonthAmount(amount);
                        newStoreAmount.setMonthAt(monthAt);
                        newStoreAmount.setProductId(proId);
                        newStoreAmount.setRemark("");
                        storeAmountService.add(newStoreAmount);
                    }

                    InstoreDetailsEntity newInstoreDetail = new InstoreDetailsEntity();
                    newInstoreDetail.setInstoreSheetId(insId);
                    newInstoreDetail.setProductId(proId);
                    newInstoreDetail.setAmount(amount);
                    newInstoreDetail.setRemark(string[i+2]);
                    instoreService.add(newInstoreDetail);
                    if(monthAt != monthAtNow){
                        //现在时间proId没有库存记录,继承最近一次proId的库存记录
                        if(storeAmountNow == null){
                            StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                            List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAtNow,proId);
                            Integer rest;
                            //如果最近一次的库存记录是入库单所在月份的话，为了防止重复添加amount，必须要记录originAmount，用来继承原来没有修改过的值（上面在赋值完originAmount后都对原来的值进行过修改）
                            // 否则在下面修改所有晚于入库单所在月份的库存记录的时候会出现重复添加amount的现象！！
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
                        //查找所有晚于入库单所在月份的proId的库存记录，并修改后面的所有库存记录
                        List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt,proId);
                        for(StoreAmountEntity storeA : laterStoreAmounts){
                            Integer rest = storeA.getThisMonthAmount();
                            storeA.setThisMonthAmount(rest + amount);
                            storeAmountService.update(storeA);
                        }
                    }
                }else { //不为null说明有这个材料 所以应该先把原来的入库数量减去还原库存表 再加上修改了的入库数量
                    Integer originAmount = instoreDetail.getAmount();

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
                        //查找所有晚于入库单所在月份的proId的库存记录，并修改后面的所有库存记录
                        List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt, proId);
                        for (StoreAmountEntity storeA : laterStoreAmounts) {
                            Integer rest = storeA.getThisMonthAmount();
                            storeA.setThisMonthAmount(rest - originAmount + amount);
                            storeAmountService.update(storeA);
                        }
                    }

                    Integer rest = storeAmount.getThisMonthAmount();
                    Integer modifiedRest = rest - originAmount + amount;
                    storeAmount.setThisMonthAmount(modifiedRest);
                    storeAmountService.update(storeAmount);

                    instoreDetail.setAmount(amount);
                    instoreDetail.setRemark(string[i+2]);
                    instoreService.update(instoreDetail);


                }

            }

            //如果此次修改删除了某些原本有的入库材料信息 就对库存表进行相应的库存数量操作
            originInstoreDetails.removeAll(removeInstoreDetails);
            if(!originInstoreDetails.isEmpty()){
                for(InstoreDetailsEntity insd : originInstoreDetails){
                    Integer proId = insd.getProductId();
                    StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt, proId);
                    StoreAmountEntity storeAmountNow = storeAmountService.findByPK(monthAtNow, proId);
                    Integer amount = insd.getAmount();
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
                        //查找所有晚于入库单所在月份的proId的库存记录，并修改后面的所有库存记录
                        List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt, proId);
                        if (laterStoreAmounts != null && !laterStoreAmounts.isEmpty()) {
                            for (StoreAmountEntity storeA : laterStoreAmounts) {
                                Integer rest = storeA.getThisMonthAmount();
                                storeA.setThisMonthAmount(rest - amount);
                                storeAmountService.update(storeA);
                            }
                        }
                    }
                    Integer rest = storeAmount.getThisMonthAmount();
                    storeAmount.setThisMonthAmount(rest - amount);
                    storeAmountService.update(storeAmount);
                    instoreService.delete(insd);
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

    public String getInstoreSheetId() {
        return instoreSheetId;
    }

    public void setInstoreSheetId(String instoreSheetId) {
        this.instoreSheetId = instoreSheetId;
    }
}

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by hp on 2015/4/29.
 */
@Controller
public class AddOutstoreSheetAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

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

    @Override
    public String execute(){
        try{

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = df.format(timestamp);
            Timestamp newTime = Timestamp.valueOf(nowTime);

            UserEntity user = userService.findByName(username);
            String realName = user.getUserRealName();
            OutstoreSheetEntity outstoreSheet = new OutstoreSheetEntity();
            outstoreSheet.setDepartment(department);
            outstoreSheet.setDateAt(newTime);
            outstoreSheet.setChecker(realName);
            outstoreService.add(outstoreSheet);

            Integer outsId = outstoreService.findIdByTime(newTime);
            String string[] = dataString.split(",");
            for(int i = 3;i < string.length;i += 3){
                OutstoreDetailsEntity outstoreDetails = new OutstoreDetailsEntity();
                Integer proId = Integer.parseInt(string[i]);
                Integer amount = Integer.parseInt(string[i+1]);
                outstoreDetails.setOutstoreSheetId(outsId);
                outstoreDetails.setProductId(proId);
                outstoreDetails.setAmount(amount);
                outstoreDetails.setRemark(string[i+2]);
                outstoreService.add(outstoreDetails);

                Calendar calendar = Calendar.getInstance();
                Integer year = calendar.get(Calendar.YEAR);
                Integer month = calendar.get(Calendar.MONTH)+1;
                Integer monthAt = year * 100 + month;
                StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt, proId);
                if ( storeAmount != null){//这个月这个product有库存信息
                    Integer rest = storeAmount.getThisMonthAmount();
                    storeAmount.setThisMonthAmount(rest - amount);
                    storeAmountService.update(storeAmount);
                }else {//这个product在这个月无库存信息
                    Integer lastTime = storeAmountService.findMaxMonthAt(proId);
                    if(lastTime != null){//这个product曾经有过库存信息
                        StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                        StoreAmountEntity lastStoreAmount = storeAmountService.findByPK(lastTime, proId);
                        newStoreAmount.setThisMonthAmount(lastStoreAmount.getThisMonthAmount() - amount);
                        newStoreAmount.setProductId(proId);
                        newStoreAmount.setRemark("");
                        newStoreAmount.setMonthAt(monthAt);
                        storeAmountService.add(newStoreAmount);
                    }else {
                        //如果没有库存就出库。。
                    }
//                    StoreAmountEntity newStoreAmount = new StoreAmountEntity();
//                    newStoreAmount.setThisMonthAmount(amount);
//                    newStoreAmount.setMonthAt(monthAt);
//                    newStoreAmount.setProductId(proId);
//                    newStoreAmount.setRemark("");
//                    storeAmountService.add(newStoreAmount);
                }
                /*----------------------------------------------------------------------------------*/
                /*          此处要有库存存量判断，若没有相应的库存信息 或 库存不足 应提示！！       */
                /*          不过最好是ajax在页面上判断！！！！！！！！！                            */
                /*----------------------------------------------------------------------------------*/
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
}

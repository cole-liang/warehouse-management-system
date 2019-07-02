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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by hp on 2015/4/29.
 */
@Controller
public class AddInstoreSheetAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

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
            InstoreSheetEntity instoreSheet = new InstoreSheetEntity();
            instoreSheet.setDepartment(department);
            instoreSheet.setDateAt(newTime);
            instoreSheet.setChecker(realName);
            instoreService.add(instoreSheet);

            Integer insId = instoreService.findIdByTime(newTime);
            String string[] = dataString.split(",");
            for(int i = 3;i < string.length;i += 3){
                InstoreDetailsEntity instoreDetails = new InstoreDetailsEntity();
                Integer proId = Integer.parseInt(string[i]);
                Integer amount = Integer.parseInt(string[i+1]);
                instoreDetails.setInstoreSheetId(insId);
                instoreDetails.setProductId(proId);
                instoreDetails.setAmount(amount);
                instoreDetails.setRemark(string[i+2]);
                instoreService.add(instoreDetails);

                Calendar calendar = Calendar.getInstance();
                Integer year = calendar.get(Calendar.YEAR);
                Integer month = calendar.get(Calendar.MONTH)+1;
                Integer monthAt = year * 100 + month;
                StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt, proId);
                if ( storeAmount != null){
                    Integer rest = storeAmount.getThisMonthAmount();
                    storeAmount.setThisMonthAmount(rest + amount);
                    storeAmountService.update(storeAmount);
                }else {
                    Integer lastTime = storeAmountService.findMaxMonthAt(proId);//获得最近一次的库存信息时间（因为有可能一个材料比如说今年1月入库，但是2、3月都没管它，4月对他进行新的入/出库操作
                                                                                   //此时2、3月都不会有该材料的库存信息，因此下面的操作，如果有之前的库存信息，就主要是将原来的库存信息继承到现在，比如说1月份还剩40个 那4月份的库存也是40个）
                    StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                    if(lastTime != null){//有该材料的库存信息 因此继承原来的库存数量进行入库操作
                        StoreAmountEntity lastStoreAmount = storeAmountService.findByPK(lastTime, proId);
                        newStoreAmount.setThisMonthAmount(amount + lastStoreAmount.getThisMonthAmount());
                    }else {//如果没有最近的库存信息 说明该材料还没入过库 直接添加填入的入库数量
                        newStoreAmount.setThisMonthAmount(amount);
                    }
                    newStoreAmount.setMonthAt(monthAt);
                    newStoreAmount.setProductId(proId);
                    newStoreAmount.setRemark("");
                    storeAmountService.add(newStoreAmount);
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
}

package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.StoreAmountEntity;
import edu.cqun.warehouse.service.IStoreAmountService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2015/4/29.
 */
@Controller
public class ValidateOutAddAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IStoreAmountService storeAmountService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String productId;
    private String amount;
    private String result;

    @Override
    public String execute(){
        Integer proId = Integer.parseInt(productId);
        Integer proAmount = Integer.parseInt(amount);
//        Integer lastTime = storeAmountService.findMaxMonthAt(proId);

        //获得当前时间的年月 供库存信息表查询用
        Calendar calendarNow = Calendar.getInstance();
        Integer yearNow = calendarNow.get(Calendar.YEAR);
        Integer monthNow = calendarNow.get(Calendar.MONTH)+1;
        Integer monthAtNow = yearNow * 100 + monthNow;

        StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAtNow,proId);
        List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAtNow, proId);
        if(earlierStoreAmounts == null || earlierStoreAmounts.isEmpty()){//之前没有库存信息
            result = "null";
        }else {
            Integer rest;
            if(storeAmount == null){
                rest = earlierStoreAmounts.get(0).getThisMonthAmount();
            }else {
                rest = storeAmount.getThisMonthAmount();
            }
//            StoreAmountEntity lastStoreAmount = storeAmountService.findByPK(lastTime, proId);
//            Integer rest = lastStoreAmount.getThisMonthAmount();
            if((rest - proAmount) < 0){
                result = "fail";
            }else {
                result = "success";
            }
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.ProductEntity;
import edu.cqun.warehouse.entity.StoreAmountEntity;
import edu.cqun.warehouse.service.IProductService;
import edu.cqun.warehouse.service.IStoreAmountService;
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
public class CheckStoreByTimeAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IStoreAmountService storeAmountService;

    @Resource
    private IProductService productService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String start;
    private String end;

    @Override
    public String execute(){
        if(start.isEmpty() && end.isEmpty()){
            List<StoreAmountEntity> storeAmounts = storeAmountService.findAllStoreAmount();
            att.put("storeAmounts",storeAmounts);
            return SUCCESS;
        }
        Timestamp startTime;
        Timestamp endTime;
        if(!start.isEmpty()){
            startTime = Timestamp.valueOf(start+" "+"00:00:00");
        }else{
            startTime = Timestamp.valueOf("1970-01-01 00:00:00");
        }
        if(!end.isEmpty()){
            endTime = Timestamp.valueOf(end+" "+"23:59:59");
        }else {
            endTime = Timestamp.valueOf("2037-12-31 23:59:59");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH)+1;
        Integer startMonthAt = year * 100 + month;
        calendar.setTime(endTime);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        Integer endMonthAt = year * 100 + month;
        List<StoreAmountEntity> list = storeAmountService.findStoreAmountByTime(startMonthAt,endMonthAt);
        List<Object> storeAmounts = new ArrayList<Object>();
        if(!list.isEmpty() || list != null){
            for(StoreAmountEntity storeAmount : list){
                Integer monthAt = storeAmount.getMonthAt();
                Integer productId = storeAmount.getProductId();
                Integer thisMonthAmount = storeAmount.getThisMonthAmount();
                String remark = storeAmount.getRemark();
                ProductEntity product = productService.findById(productId);
                String proName = product.getProductName();
                Object[] objects = new Object[]{monthAt, productId, proName, thisMonthAmount, remark};
                storeAmounts.add(objects);
            }
        }
        att.put("storeAmounts",storeAmounts);
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

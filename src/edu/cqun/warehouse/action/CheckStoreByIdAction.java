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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2015/4/29.
 */
@Controller
public class CheckStoreByIdAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IStoreAmountService storeAmountService;

    @Resource
    private IProductService productService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String storeAmountId;

    @Override
    public String execute(){
        List<Object> storeAmounts = new ArrayList<Object>();
        List<StoreAmountEntity> list = storeAmountService.findStoreAmountByProductId(Integer.parseInt(storeAmountId));
        if(!list.isEmpty() || list != null) {
            for (StoreAmountEntity storeAmount : list) {
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

    public String getStoreAmountId() {
        return storeAmountId;
    }

    public void setStoreAmountId(String storeAmountId) {
        this.storeAmountId = storeAmountId;
    }
}

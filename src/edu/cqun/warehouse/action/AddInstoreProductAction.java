package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.ProductEntity;
import edu.cqun.warehouse.service.IProductService;
import edu.cqun.warehouse.service.Impl.ProductService;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AddInstoreProductAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IProductService productService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String proId;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String execute(){
        try{
            Integer productId = Integer.parseInt(proId);
            ProductEntity product = productService.findById(productId);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("productId",product.getProductId());
            map.put("model",product.getModel());
            map.put("price",product.getPrice());
            map.put("productName",product.getProductName());
            map.put("productUnit",product.getUnit());
            map.put("supplier",product.getSupplier());

            JSONObject json = JSONObject.fromObject(map);
            result = json.toString();
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
}

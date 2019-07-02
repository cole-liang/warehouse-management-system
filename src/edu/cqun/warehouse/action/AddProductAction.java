package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.ProductEntity;
import edu.cqun.warehouse.service.IProductService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2015/4/29.
 */
@Controller
public class AddProductAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IProductService productService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String productId;
    private String productName;
    private String model;
    private String unit;
    private String price;
    private String supplier;

    @Override
    public String execute(){
        Integer proId = Integer.parseInt(productId);
        Float proPrice = Float.parseFloat(price);
        ProductEntity product = new ProductEntity();
        product.setModel(model);
        product.setPrice(proPrice);
        product.setProductId(proId);
        product.setProductName(productName);
        product.setSupplier(supplier);
        product.setUnit(unit);
        productService.add(product);
        List<ProductEntity> products = productService.findAllProduct();
        att.put("products",products);
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}

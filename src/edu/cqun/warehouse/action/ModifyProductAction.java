package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.*;
import edu.cqun.warehouse.service.IInstoreService;
import edu.cqun.warehouse.service.IOutstoreService;
import edu.cqun.warehouse.service.IProductService;
import edu.cqun.warehouse.service.IStoreAmountService;
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
public class ModifyProductAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IProductService productService;

//    @Resource
//    private IInstoreService instoreService;
//
//    @Resource
//    private IOutstoreService outstoreService;
//
//    @Resource
//    private IStoreAmountService storeAmountService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String originId;
    private String productName;
    private String model;
    private String unit;
    private String price;
    private String supplier;

    @Override
    public String execute(){
        Integer originProId = Integer.parseInt(originId);
//        Integer modifiedProId = Integer.parseInt(modifiedId);
        Float proPrice = Float.parseFloat(price);
        ProductEntity product = new ProductEntity();
        product.setModel(model);
        product.setPrice(proPrice);
        product.setProductId(originProId);
        product.setProductName(productName);
        product.setSupplier(supplier);
        product.setUnit(unit);
//        if(originProId == modifiedProId){
        productService.update(product);
//        }else{
//            productService.add(product);
//            List<InstoreDetailsEntity> instoreDetails = instoreService.findInstoreDetailsByProductId(originProId);
//            for(InstoreDetailsEntity instoreDetail : instoreDetails){
//                instoreDetail.setProductId(modifiedProId);
//                instoreService.update(instoreDetail);
//            }
//            List<OutstoreDetailsEntity> outstoreDetails = outstoreService.findOutstoreDetailsByProductId(originProId);
//            for(OutstoreDetailsEntity outstoreDetail : outstoreDetails){
//                outstoreDetail.setProductId(modifiedProId);
//                outstoreService.update(outstoreDetail);
//            }
//            List<StoreAmountEntity> storeAmounts = storeAmountService.findStoreAmountByProductId(originProId);
//            for(StoreAmountEntity storeAmount : storeAmounts){
//                storeAmount.setProductId(modifiedProId);
//                storeAmountService.update(storeAmount);
//            }
//            ProductEntity productEntity = productService.findById(originProId);
//            productService.delete(productEntity);
//        }
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

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

//    public String getModifiedId() {
//        return modifiedId;
//    }
//
//    public void setModifiedId(String modifiedId) {
//        this.modifiedId = modifiedId;
//    }

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

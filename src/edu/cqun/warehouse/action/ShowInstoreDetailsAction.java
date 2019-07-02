package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.InstoreDetailsEntity;
import edu.cqun.warehouse.entity.InstoreSheetEntity;
import edu.cqun.warehouse.entity.ProductEntity;
import edu.cqun.warehouse.service.IInstoreService;
import edu.cqun.warehouse.service.IProductService;
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
public class ShowInstoreDetailsAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IInstoreService instoreService;

    @Resource
    private IProductService productService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String instoreSheetId;
    private String username;

    @Override
    public String execute(){
        Integer id = Integer.parseInt(instoreSheetId);
        List<InstoreDetailsEntity> instoreDetails = instoreService.findInstoreDetailsById(id);
        InstoreSheetEntity instoreSheet = instoreService.findInstoreSheetById(id);
        String department = instoreSheet.getDepartment();
        List<Object[]> list = new ArrayList<Object[]>();
        for(InstoreDetailsEntity insd : instoreDetails){
            Integer insId = insd.getInstoreSheetId();
            Integer proId = insd.getProductId();
            Integer amount = insd.getAmount();
            String remark = insd.getRemark();
            ProductEntity product = productService.findById(proId);
            String proName = product.getProductName();
            String model = product.getModel();
            String unit = product.getUnit();
            Float price = product.getPrice();
            String supplier = product.getSupplier();
            Object[] objects = new Object[] {proId, proName, model, unit, price, supplier, amount, remark};
            list.add(objects);
        }
        att.put("instoreSheetId",instoreSheetId);
        att.put("list",list);
        att.put("department",department);
        att.put("username",username);
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

    public String getInstoreSheetId() {
        return instoreSheetId;
    }

    public void setInstoreSheetId(String instoreSheetId) {
        this.instoreSheetId = instoreSheetId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

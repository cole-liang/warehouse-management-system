package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.ProductEntity;
import edu.cqun.warehouse.service.IProductService;
import edu.cqun.warehouse.service.IUserService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class ProductManagerAcition extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware {

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IProductService productService;

    @Resource
    private IUserService userService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public String execute(){
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
}


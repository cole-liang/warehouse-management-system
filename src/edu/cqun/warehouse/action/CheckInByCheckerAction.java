package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.InstoreSheetEntity;
import edu.cqun.warehouse.service.IInstoreService;
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
public class CheckInByCheckerAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IInstoreService instoreService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String checker;

    @Override
    public String execute(){
        List<InstoreSheetEntity> list = instoreService.findInstoreSheetByChecker(checker);
        att.put("instoreSheets",list);
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

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }
}

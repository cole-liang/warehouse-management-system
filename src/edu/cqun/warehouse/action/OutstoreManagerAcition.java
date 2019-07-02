package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.OutstoreSheetEntity;
import edu.cqun.warehouse.entity.UserEntity;
import edu.cqun.warehouse.service.IOutstoreService;
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
public class OutstoreManagerAcition extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware {

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IOutstoreService outstoreService;

    @Resource
    private IUserService userService;

//    public static Integer page;
//    private Integer maxPage;
//    private Integer rows = 3;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String username;

    @Override
    public String execute(){
        List<OutstoreSheetEntity> outstoreSheets = outstoreService.findAllOutstoreSheet();
        UserEntity user = userService.findByName(username);
        String level = user.getUserLevel();
        att.put("level",level);
        att.put("outstoreSheets",outstoreSheets);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.OutstoreSheetEntity;
import edu.cqun.warehouse.service.IOutstoreService;
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
public class CheckOutByIdAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IOutstoreService outstoreService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String outstoreSheetId;

    @Override
    public String execute(){
        List<OutstoreSheetEntity> list = new ArrayList<OutstoreSheetEntity>();
        list.add(outstoreService.findOutstoreSheetById(Integer.parseInt(outstoreSheetId)));
        att.put("outstoreSheets",list);
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

    public String getOutstoreSheetId() {
        return outstoreSheetId;
    }

    public void setOutstoreSheetId(String outstoreSheetId) {
        this.outstoreSheetId = outstoreSheetId;
    }
}

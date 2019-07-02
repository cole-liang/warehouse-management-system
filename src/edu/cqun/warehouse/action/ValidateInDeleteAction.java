package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.InstoreDetailsEntity;
import edu.cqun.warehouse.entity.InstoreSheetEntity;
import edu.cqun.warehouse.entity.StoreAmountEntity;
import edu.cqun.warehouse.service.IInstoreService;
import edu.cqun.warehouse.service.IStoreAmountService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2015/4/29.
 */
@Controller
public class ValidateInDeleteAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IInstoreService instoreService;

    @Resource
    private IStoreAmountService storeAmountService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String instoreSheetId;
    private String result;

    @Override
    public String execute(){
        Integer insId = Integer.parseInt(instoreSheetId);
        InstoreSheetEntity instoreSheet = instoreService.findInstoreSheetById(insId);

        //获得当前入库单年月 供库存信息表查询用
        Timestamp timestamp2 = instoreSheet.getDateAt();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp2);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH)+1;
        Integer monthAt = year * 100 + month;

        //获得修改时间年月 供库存信息表查询用
        Calendar calendarNow = Calendar.getInstance();
        Integer yearNow = calendarNow.get(Calendar.YEAR);
        Integer monthNow = calendarNow.get(Calendar.MONTH)+1;
        Integer monthAtNow = yearNow * 100 + monthNow;

        List<InstoreDetailsEntity> instoreDetails = instoreService.findInstoreDetailsById(insId);
        result = "success";
        for(InstoreDetailsEntity insd : instoreDetails){
            Integer proId = insd.getProductId();
            Integer originAmount = insd.getAmount();
            StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt,proId);
            if(monthAt != monthAtNow) {
                List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt, proId);
                if (laterStoreAmounts != null && !laterStoreAmounts.isEmpty()) {
                    for (StoreAmountEntity storeA : laterStoreAmounts) {
                        Integer rest = storeA.getThisMonthAmount();
                        if((rest - originAmount) < 0){
                            result = storeA.getMonthAt().toString();
                            break;
                        }
                    }
                }
            }
            Integer rest = storeAmount.getThisMonthAmount();
            if((rest - originAmount) < 0){
                result = storeAmount.getMonthAt().toString();
                break;
            }
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

    public String getInstoreSheetId() {
        return instoreSheetId;
    }

    public void setInstoreSheetId(String instoreSheetId) {
        this.instoreSheetId = instoreSheetId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

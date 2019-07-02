package edu.cqun.warehouse.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.cqun.warehouse.entity.OutstoreDetailsEntity;
import edu.cqun.warehouse.entity.OutstoreSheetEntity;
import edu.cqun.warehouse.entity.StoreAmountEntity;
import edu.cqun.warehouse.service.IOutstoreService;
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
public class DeleteOutstoreSheetAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IOutstoreService outstoreService;

    @Resource
    private IStoreAmountService storeAmountService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String pageType;
    private String outstoreSheetId;

    @Override
    public String execute(){
        Integer insId = Integer.parseInt(outstoreSheetId);
        List<OutstoreDetailsEntity> outstoreDetails = outstoreService.findOutstoreDetailsById(insId);
        OutstoreSheetEntity outstoreSheet = outstoreService.findOutstoreSheetById(insId);

        Timestamp timestamp2 = outstoreSheet.getDateAt();
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

        for (OutstoreDetailsEntity outstoreDetail : outstoreDetails){
            Integer amount = outstoreDetail.getAmount();
            Integer proId = outstoreDetail.getProductId();
            StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt, proId);
            StoreAmountEntity storeAmountNow = storeAmountService.findByPK(monthAtNow, proId);
            if(monthAt != monthAtNow) {
                //现在时间proId没有库存记录,继承最近一次proId的库存记录
                if (storeAmountNow == null) {
                    StoreAmountEntity newStoreAmount = new StoreAmountEntity();
                    List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAtNow, proId);
                    Integer rest = earlierStoreAmounts.get(0).getThisMonthAmount();
                    newStoreAmount.setThisMonthAmount(rest);
                    newStoreAmount.setMonthAt(monthAtNow);
                    newStoreAmount.setProductId(proId);
                    newStoreAmount.setRemark("");
                    storeAmountService.add(newStoreAmount);
                }
                //查找所有晚于入库单所在月份的proId的库存记录，并修改后面的所有库存记录
                List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt, proId);
                if (laterStoreAmounts != null && !laterStoreAmounts.isEmpty()) {
                    for (StoreAmountEntity storeA : laterStoreAmounts) {
                        Integer rest = storeA.getThisMonthAmount();
                        storeA.setThisMonthAmount(rest + amount);
                        storeAmountService.update(storeA);
                    }
                }
            }
            Integer rest = storeAmount.getThisMonthAmount();
            Integer modifiedRest = rest + amount;
            storeAmount.setThisMonthAmount(modifiedRest);
            storeAmountService.update(storeAmount);
            outstoreService.delete(outstoreDetail);
        }
        outstoreService.delete(outstoreSheet);
        List<OutstoreSheetEntity> outstoreSheets = outstoreService.findAllOutstoreSheet();
        att.put("outstoreSheets",outstoreSheets);
        return pageType;
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

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getOutstoreSheetId() {
        return outstoreSheetId;
    }

    public void setOutstoreSheetId(String outstoreSheetId) {
        this.outstoreSheetId = outstoreSheetId;
    }
}

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
public class ValidateOutModifyAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware{

    private static final long serialVersionUID = -7977697013453779402L;

    @Resource
    private IOutstoreService outstoreService;

    @Resource
    private IStoreAmountService storeAmountService;

    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private String outstoreSheetId;
    private String productId;
    private String amount;
    private String result;

    @Override
    public String execute(){
        Integer outsId = Integer.parseInt(outstoreSheetId);
        Integer proId = Integer.parseInt(productId);
        Integer proAmount = Integer.parseInt(amount);
        OutstoreSheetEntity outstoreSheet = outstoreService.findOutstoreSheetById(outsId);

        //获得修改时间年月 供库存信息表查询用
        Calendar calendarNow = Calendar.getInstance();
        Integer yearNow = calendarNow.get(Calendar.YEAR);
        Integer monthNow = calendarNow.get(Calendar.MONTH)+1;
        Integer monthAtNow = yearNow * 100 + monthNow;

        //获得当前出库单年月 供库存信息表查询用
        Timestamp timestamp2 = outstoreSheet.getDateAt();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp2);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH)+1;
        Integer monthAt = year * 100 + month;

        OutstoreDetailsEntity outstoreDetail = outstoreService.findOutstoreDetailsByPK(outsId, proId);
        List<StoreAmountEntity> earlierStoreAmounts = storeAmountService.findByCompareBeneath(monthAtNow, proId);
        StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt, proId);
//        Integer lastTime = storeAmountService.findMaxMonthAt(proId);
        if(outstoreDetail == null){
            if(earlierStoreAmounts == null || earlierStoreAmounts.isEmpty()){//之前没有库存信息
                result = "null";
            }else {
//                StoreAmountEntity lastStoreAmount = storeAmountService.findByPK(lastTime, proId);
//                Integer rest = lastStoreAmount.getThisMonthAmount();
                result = "success";
                if(monthAt != monthAtNow) {
                    List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt, proId);
                    if (laterStoreAmounts != null && !laterStoreAmounts.isEmpty()) {
                        for (StoreAmountEntity storeA : laterStoreAmounts) {
                            Integer rest = storeA.getThisMonthAmount();
                            if((rest - proAmount) < 0){
                                result = storeA.getMonthAt().toString();
                                break;
                            }
                        }
                    }
                }

                if(storeAmount == null){
                    Integer rest = earlierStoreAmounts.get(0).getThisMonthAmount();
                    if ((rest -  proAmount) < 0) {
                        result = earlierStoreAmounts.get(0).getMonthAt().toString();
                    }
                }else{
                    Integer rest = storeAmount.getThisMonthAmount();
                    if ((rest -  proAmount) < 0) {
                        result = storeAmount.getMonthAt().toString();
                    }
                }

//                Integer rest = earlierStoreAmounts.get(0).getThisMonthAmount();
//                if((rest - proAmount) < 0){
//                    result = "fail";
//                }else {
//                    result = "success";
//                }
            }
        }else {
//            OutstoreSheetEntity outstoreSheet = outstoreService.findOutstoreSheetById(outsId);
//
//            //获得当前出库单年月 供库存信息表查询用
//            Timestamp timestamp2 = outstoreSheet.getDateAt();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(timestamp2);
//            Integer year = calendar.get(Calendar.YEAR);
//            Integer month = calendar.get(Calendar.MONTH)+1;
//            Integer monthAt = year * 100 + month;

//            StoreAmountEntity storeAmount = storeAmountService.findByPK(monthAt,proId);
//            StoreAmountEntity lastStoreAmount = storeAmountService.findByPK(lastTime, proId);
//            Integer rest = lastStoreAmount.getThisMonthAmount();
            Integer originAmount = outstoreDetail.getAmount();
            result = "success";
            if(monthAt != monthAtNow) {
                List<StoreAmountEntity> laterStoreAmounts = storeAmountService.findByCompareAbove(monthAt, proId);
                if (laterStoreAmounts != null && !laterStoreAmounts.isEmpty()) {
                    for (StoreAmountEntity storeA : laterStoreAmounts) {
                        Integer rest = storeA.getThisMonthAmount();
                        if((rest + originAmount - proAmount) < 0){
                            result = storeA.getMonthAt().toString();
                            break;
                        }
                    }
                }
            }

            Integer rest = storeAmount.getThisMonthAmount();
            if((rest + originAmount - proAmount) < 0){
                result = storeAmount.getMonthAt().toString();
            }
//            if((rest + originAmount - proAmount) < 0){
//                result = "fail";
//            }else {
//                result = "success";
//            }
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOutstoreSheetId() {
        return outstoreSheetId;
    }

    public void setOutstoreSheetId(String outstoreSheetId) {
        this.outstoreSheetId = outstoreSheetId;
    }
}

package edu.cqun.warehouse.test;

import edu.cqun.warehouse.entity.InstoreDetailsEntity;
import edu.cqun.warehouse.entity.InstoreSheetEntity;
import edu.cqun.warehouse.service.IInstoreService;
import edu.cqun.warehouse.service.IStoreAmountService;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hp on 2015/5/7.
 */
public class Test {
    InstoreSheetEntity instoreSheetEntity = new InstoreSheetEntity();

//    @Resource
//    private IInstoreService instoreService;
//
//    @Resource
//    private IStoreAmountService storeAmountService;

    public Test(){
       this.instoreSheetEntity.setChecker("ccc");
        this.instoreSheetEntity.setDateAt(new Timestamp(System.currentTimeMillis()));
        this.instoreSheetEntity.setDepartment("aaaa");
        this.instoreSheetEntity.setInstoreSheetId(112);
    }

    public InstoreSheetEntity getInstoreSheetEntity() {
        return instoreSheetEntity;
    }

    public void setInstoreSheetEntity(InstoreSheetEntity instoreSheetEntity) {
        this.instoreSheetEntity = instoreSheetEntity;
    }

//    public Integer findMax(Integer proId){
//        return storeAmountService.findMaxMonthAt(proId);
//    }
//
//
//    public List<InstoreDetailsEntity> find(){
//        List<InstoreDetailsEntity> instoreDetails = instoreService.findInstoreDetailsById(9);
//        return instoreDetails;
//    }

    public static void main(String[] args) {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        int time = timestamp.getDate();
//        System.out.println(time);
//        Calendar calendar = Calendar.getInstance();
//        Timestamp timestamp = Timestamp.valueOf("2015-05-20"+" "+"00:00:00");
//        calendar.setTime(timestamp);
////        calendar.setTime(timestamp);
//        Integer year = calendar.get(Calendar.YEAR);
//        Integer month = calendar.get(Calendar.MONTH)+1;
//        Integer monthAt = year * 100 + month;
//        System.out.println(monthAt);
//        List<String> list = new ArrayList<String>();
//        List<String> list2 = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list2.add("2");
//        list2.add("3");
//        list.addAll(list2);
//        for(String s : list){
//            System.out.println(s);
//        }
//        Integer a = 1;
//        Integer b = 1;
//        if(a == b){
//            System.out.println("equal");
//        }
//        List<InstoreDetailsEntity> instoreDetails = new ArrayList<InstoreDetailsEntity>();
//        InstoreDetailsEntity instoreDetailsEntity1 = new InstoreDetailsEntity();
//        instoreDetailsEntity1.setInstoreSheetId(1);
//        instoreDetailsEntity1.setAmount(123);
//        instoreDetailsEntity1.setProductId(23324);
//        instoreDetailsEntity1.setRemark("hoho");
//        InstoreDetailsEntity instoreDetailsEntity2 = new InstoreDetailsEntity();
//        instoreDetailsEntity2.setInstoreSheetId(2);
//        instoreDetailsEntity2.setAmount(999);
//        instoreDetailsEntity2.setProductId(999);
//        instoreDetailsEntity2.setRemark("yeah");
//        instoreDetails.add(instoreDetailsEntity1);
//        instoreDetails.add(instoreDetailsEntity2);
//        for (InstoreDetailsEntity ins:instoreDetails){
//            System.out.println(ins.toString());
//        }
//        instoreDetails.remove(instoreDetailsEntity2);
//        for (InstoreDetailsEntity ins:instoreDetails){
//            System.out.println(ins.toString());
//        }
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String nowTime = df.format(timestamp);
//            Timestamp newTime = Timestamp.valueOf(nowTime);
//        System.out.println(timestamp.toString());
//        System.out.println(newTime.toString());
//        Test test = new Test();
//        Integer result = test.findMax(7);
//        System.out.println(result);
//        List<InstoreDetailsEntity> instoreDetails = test.find();
//        for(InstoreDetailsEntity instoreDetailsEntity : instoreDetails){
//            System.out.println(instoreDetailsEntity.toString());
//        }
    }
}

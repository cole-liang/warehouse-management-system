package edu.cqun.warehouse.service.Impl;

import edu.cqun.warehouse.dao.IBaseDAO;
import edu.cqun.warehouse.entity.StoreAmountEntity;
import edu.cqun.warehouse.service.IStoreAmountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("storeAmountService")
public class StoreAmountService implements IStoreAmountService{

    @Resource
    private IBaseDAO<StoreAmountEntity> storeAmountDAO;

    @Override
    public List<StoreAmountEntity> findAllStoreAmount() {
        return storeAmountDAO.find("from StoreAmountEntity");
    }

    @Override
    public StoreAmountEntity findByPK(Integer monthAt, Integer productId) {
        List<Object> list = new ArrayList<Object>();
        list.add(monthAt);
        list.add(productId);
        return storeAmountDAO.get("from StoreAmountEntity where monthAt =? and productId =?",list);
    }

    @Override
    public List<StoreAmountEntity> findStoreAmountByTime(Integer startMonthAt, Integer endMonthAt) {
        List<Object> list = new ArrayList<Object>();
        list.add(startMonthAt);
        list.add(endMonthAt);
        return storeAmountDAO.find("from StoreAmountEntity st where st.monthAt between ? and ?",list);
    }

    @Override
    public List<StoreAmountEntity> findStoreAmountByProductId(Integer productId) {
        List<Object> list = new ArrayList<Object>();
        list.add(productId);
        return storeAmountDAO.find("from StoreAmountEntity where productId =?",list);
    }

    @Override
    public Integer findMaxMonthAt(Integer productId) {
        List<Object> list = new ArrayList<Object>();
        list.add(productId);
        return (Integer) storeAmountDAO.findElement("select max(storeA.monthAt) from StoreAmountEntity storeA where storeA.productId =?",list);
    }

    @Override
    public List<StoreAmountEntity> findByCompareAbove(Integer monthAt, Integer proId) {
        List<Object> list = new ArrayList<Object>();
        list.add(monthAt);
        list.add(proId);
        return storeAmountDAO.find("from StoreAmountEntity storeA where storeA.monthAt > ? and storeA.productId = ? order by storeA.monthAt desc",list);
    }

    @Override
    public List<StoreAmountEntity> findByCompareBeneath(Integer monthAt, Integer proId) {
        List<Object> list = new ArrayList<Object>();
        list.add(monthAt);
        list.add(proId);
        return storeAmountDAO.find("from StoreAmountEntity storeA where storeA.monthAt < ? and storeA.productId = ? order by storeA.monthAt desc",list);
    }

    @Override
    public void add(StoreAmountEntity storeAmount) {
        storeAmountDAO.save(storeAmount);
    }

    @Override
    public void delete(StoreAmountEntity storeAmount) {
        storeAmountDAO.delete(storeAmount);
    }

    @Override
    public void update(StoreAmountEntity storeAmount) {
        storeAmountDAO.update(storeAmount);
    }
}

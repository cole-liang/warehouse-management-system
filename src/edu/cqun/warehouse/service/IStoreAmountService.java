package edu.cqun.warehouse.service;

import edu.cqun.warehouse.entity.StoreAmountEntity;

import java.util.List;

public interface IStoreAmountService {

    /**
     * 查找全部库存信息
     * @return
     */
    public List<StoreAmountEntity> findAllStoreAmount();

    /**
     * 根据主键找库存信息
     * @param monthAt
     * @param productId
     * @return
     */
    public StoreAmountEntity findByPK(Integer monthAt,Integer productId);

    /**
     * 根据时间范围找库存信息
     * @param startMonthAt
     * @param endMonthAt
     * @return
     */
    public List<StoreAmountEntity> findStoreAmountByTime(Integer startMonthAt, Integer endMonthAt);

    /**
     * 根据材料编号找库存信息
     * @param productId
     * @return
     */
    public List<StoreAmountEntity> findStoreAmountByProductId(Integer productId);

    /**
     * 根据材料编号查找最晚时间
     * @param productId
     * @return
     */
    public Integer findMaxMonthAt(Integer productId);

    /**
     * 查找晚于monthAt的所有库存信息
     * @param monthAt
     * @return
     */
    public List<StoreAmountEntity> findByCompareAbove(Integer monthAt, Integer proId);

    /**
     * 查找早于monthAt的所有库存信息，并按时间降序排序
     * @param monthAt
     * @return
     */
    public List<StoreAmountEntity> findByCompareBeneath(Integer monthAt, Integer proId);

    /**
     * 添加库存信息
     * @param storeAmount
     */
    public void add(StoreAmountEntity storeAmount);

    /**
     * 删除库存信息
     * @param storeAmount
     */
    public void delete(StoreAmountEntity storeAmount);

    /**
     * 更新库存信息
     * @param storeAmount
     */
    public void update(StoreAmountEntity storeAmount);
}

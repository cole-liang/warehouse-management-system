package edu.cqun.warehouse.service;

import edu.cqun.warehouse.entity.OutstoreDetailsEntity;
import edu.cqun.warehouse.entity.OutstoreSheetEntity;

import java.sql.Timestamp;
import java.util.List;

public interface IOutstoreService {

    /**
     * 查找全部
     * @return
     */
    public List<OutstoreDetailsEntity> findAllOutstoreDetails();

    /**
     * 查找全部
     * @return
     */
    public List<OutstoreSheetEntity> findAllOutstoreSheet();

    /**
     * 根据录入时间查询ID
     * @param time
     * @return
     */
    public Integer findIdByTime(Timestamp time);

    /**
     * 查询最近一次的录入时间
     * @return
     */
    public Timestamp findLatestTime();

    /**
     * 根据页码查找
     * @param page
     * @param rows
     * @return
     */
    public List<OutstoreDetailsEntity> findOutstoreDetailsByPage(int page,int rows);

    /**
     * 根据页码查找
     * @param page
     * @param rows
     * @return
     */
    public List<OutstoreSheetEntity> findOutstoreSheetByPage(int page,int rows);

    /**
     * 根据入库单ID查找对应出库单详情
     * @param outstoreSheetId
     * @return
     */
    public List<OutstoreDetailsEntity> findOutstoreDetailsById(Integer outstoreSheetId);

    /**
     * 根据材料ID查找对应入库单详情
     * @param productId
     * @return
     */
    public List<OutstoreDetailsEntity> findOutstoreDetailsByProductId(Integer productId);

    /**
     * 根据入库单ID和材料ID查找对应出库单详情
     * @param outstoreSheetId
     * @param productId
     * @return
     */
    public OutstoreDetailsEntity findOutstoreDetailsByPK(Integer outstoreSheetId, Integer productId);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    public OutstoreSheetEntity findOutstoreSheetById(Integer id);

    /**
     * 根据经手人查找
     * @param checker
     * @return
     */
    public List<OutstoreSheetEntity> findOutstoreSheetByChecker(String checker);

    /**
     * 根据领货部门查找
     * @param department
     * @return
     */
    public List<OutstoreSheetEntity> findOutstoreSheetByDept(String department);

    /**
     * 根据时间范围查找
     * @param start
     * @param end
     * @return
     */
    public List<OutstoreSheetEntity> findOutstoreSheetByTime(Timestamp start, Timestamp end);

    /**
     * 添加
     * @param outstoreDetails
     */
    public void add(OutstoreDetailsEntity outstoreDetails);

    /**
     * 添加
     * @param outstoreSheet
     */
    public void add(OutstoreSheetEntity outstoreSheet);

    /**
     * 删除
     * @param outstoreDetails
     */
    public void delete(OutstoreDetailsEntity outstoreDetails);

    /**
     * 删除
     * @param outstoreSheet
     */
    public void delete(OutstoreSheetEntity outstoreSheet);

    /**
     * 更新
     * @param outstoreDetails
     */
    public void update(OutstoreDetailsEntity outstoreDetails);

    /**
     * 更新
     * @param outstoreSheet
     */
    public void update(OutstoreSheetEntity outstoreSheet);
}

package edu.cqun.warehouse.service;

import edu.cqun.warehouse.entity.InstoreDetailsEntity;
import edu.cqun.warehouse.entity.InstoreSheetEntity;

import java.sql.Timestamp;
import java.util.List;

public interface IInstoreService {

    /**
     * 查找全部
     * @return
     */
    public List<InstoreDetailsEntity> findAllInstoreDetails();

    /**
     * 查找全部
     * @return
     */
    public List<InstoreSheetEntity> findAllInstoreSheet();

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
    public List<InstoreDetailsEntity> findInstoreDetailsByPage(int page,int rows);

    /**
     * 根据页码查找
     * @param page
     * @param rows
     * @return
     */
    public List<InstoreSheetEntity> findInstoreSheetByPage(int page,int rows);

    /**
     * 根据入库单ID查找对应入库单详情
     * @param instoreSheetId
     * @return
     */
    public List<InstoreDetailsEntity> findInstoreDetailsById(Integer instoreSheetId);

    /**
     * 根据材料ID查找对应入库单详情
     * @param productId
     * @return
     */
    public List<InstoreDetailsEntity> findInstoreDetailsByProductId(Integer productId);

    /**
     * 根据入库单ID和材料ID查找对应入库单详情
     * @param instoreSheetId
     * @param productId
     * @return
     */
    public InstoreDetailsEntity findInstoreDetailsByPK(Integer instoreSheetId, Integer productId);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    public InstoreSheetEntity findInstoreSheetById(Integer id);

    /**
     * 根据经手人查找
     * @param checker
     * @return
     */
    public List<InstoreSheetEntity> findInstoreSheetByChecker(String checker);

    /**
     * 根据领货部门查找
     * @param department
     * @return
     */
    public List<InstoreSheetEntity> findInstoreSheetByDept(String department);

    /**
     * 根据时间范围查找
     * @param start
     * @param end
     * @return
     */
    public List<InstoreSheetEntity> findInstoreSheetByTime(Timestamp start, Timestamp end);

    /**
     * 添加
     * @param instoreDetails
     */
    public void add(InstoreDetailsEntity instoreDetails);

    /**
     * 添加
     * @param instoreSheet
     */
    public void add(InstoreSheetEntity instoreSheet);

    /**
     * 删除
     * @param instoreDetails
     */
    public void delete(InstoreDetailsEntity instoreDetails);

    /**
     * 删除
     * @param instoreSheet
     */
    public void delete(InstoreSheetEntity instoreSheet);

    /**
     * 更新
     * @param instoreDetails
     */
    public void update(InstoreDetailsEntity instoreDetails);

    /**
     * 更新
     * @param instoreSheet
     */
    public void update(InstoreSheetEntity instoreSheet);
}

package edu.cqun.warehouse.service.Impl;

import edu.cqun.warehouse.dao.IBaseDAO;
import edu.cqun.warehouse.entity.OutstoreDetailsEntity;
import edu.cqun.warehouse.entity.OutstoreSheetEntity;
import edu.cqun.warehouse.service.IOutstoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("outstoreService")
public class OutstoreService implements IOutstoreService {

    @Resource
    private IBaseDAO<OutstoreDetailsEntity> outstoreDetailsDAO;

    @Resource
    private IBaseDAO<OutstoreSheetEntity> outstoreSheetDAO;

    @Override
    public List<OutstoreDetailsEntity> findAllOutstoreDetails() {
        return outstoreDetailsDAO.find("from OutstoreDetailsEntity");
    }

    @Override
    public List<OutstoreSheetEntity> findAllOutstoreSheet() {
        return outstoreSheetDAO.find("from OutstoreSheetEntity outs order by outs.dateAt desc");
    }

    @Override
    public Integer findIdByTime(Timestamp time) {
        List<Object> list = new ArrayList<Object>();
        list.add(time);
        return (Integer) outstoreSheetDAO.findElement("select outs.outstoreSheetId from OutstoreSheetEntity outs where dateAt =?",list);
    }

    @Override
    public Timestamp findLatestTime() {
        return (Timestamp) outstoreSheetDAO.findElement("select max(outs.dateAt) from OutstoreSheetEntity outs");
    }

    @Override
    public List<OutstoreDetailsEntity> findOutstoreDetailsByPage(int page, int rows) {
        List<Object> list = null;
        return outstoreDetailsDAO.find("from OutstoreDetailsEntity",list,page,rows);
    }

    @Override
    public List<OutstoreSheetEntity> findOutstoreSheetByPage(int page, int rows) {
        List<Object> list = null;
        return outstoreSheetDAO.find("from OutstoreSheetEntity outs order by outs.dateAt desc",list,page,rows);
    }

    @Override
    public List<OutstoreDetailsEntity> findOutstoreDetailsById(Integer outstoreSheetId) {
        List<Object> list = new ArrayList<Object>();
        list.add(outstoreSheetId);
        return outstoreDetailsDAO.find("from OutstoreDetailsEntity where outstoreSheetId =?", list);
    }

    @Override
    public List<OutstoreDetailsEntity> findOutstoreDetailsByProductId(Integer productId) {
        List<Object> list = new ArrayList<Object>();
        list.add(productId);
        return outstoreDetailsDAO.find("from OutstoreDetailsEntity where productId =?", list);
    }

    @Override
    public OutstoreDetailsEntity findOutstoreDetailsByPK(Integer outstoreSheetId, Integer productId) {
        List<Object> list = new ArrayList<Object>();
        list.add(outstoreSheetId);
        list.add(productId);
        return outstoreDetailsDAO.get("from OutstoreDetailsEntity where outstoreSheetId =? and productId =?", list);
    }

    @Override
    public OutstoreSheetEntity findOutstoreSheetById(Integer id) {
        List<Object> list = new ArrayList<Object>();
        list.add(id);
        return outstoreSheetDAO.get("from OutstoreSheetEntity where outstoreSheetId =?", list);
    }

    @Override
    public List<OutstoreSheetEntity> findOutstoreSheetByChecker(String checker) {
        List<Object> list = new ArrayList<Object>();
        list.add(checker);
        return outstoreSheetDAO.find("from OutstoreSheetEntity where checker =?", list);
    }

    @Override
    public List<OutstoreSheetEntity> findOutstoreSheetByDept(String department) {
        List<Object> list = new ArrayList<Object>();
        list.add(department);
        return outstoreSheetDAO.find("from OutstoreSheetEntity where department =?", list);
    }

    @Override
    public List<OutstoreSheetEntity> findOutstoreSheetByTime(Timestamp start, Timestamp end) {
        List<Object> list = new ArrayList<Object>();
        list.add(start);
        list.add(end);
        return outstoreSheetDAO.find("from OutstoreSheetEntity outs where outs.dateAt between ? and ?",list);
    }

    @Override
    public void add(OutstoreDetailsEntity outstoreDetails) {
        outstoreDetailsDAO.save(outstoreDetails);
    }

    @Override
    public void add(OutstoreSheetEntity outstoreSheet) {
        outstoreSheetDAO.save(outstoreSheet);
    }

    @Override
    public void delete(OutstoreDetailsEntity outstoreDetails) {
        outstoreDetailsDAO.delete(outstoreDetails);
    }

    @Override
    public void delete(OutstoreSheetEntity outstoreSheet) {
        outstoreSheetDAO.delete(outstoreSheet);
    }

    @Override
    public void update(OutstoreDetailsEntity outstoreDetails) {
        outstoreDetailsDAO.update(outstoreDetails);
    }

    @Override
    public void update(OutstoreSheetEntity outstoreSheet) {
        outstoreSheetDAO.update(outstoreSheet);
    }
}

package edu.cqun.warehouse.service.Impl;

import edu.cqun.warehouse.dao.IBaseDAO;
import edu.cqun.warehouse.entity.InstoreDetailsEntity;
import edu.cqun.warehouse.entity.InstoreSheetEntity;
import edu.cqun.warehouse.service.IInstoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("instoreService")
public class InstoreService implements IInstoreService{

    @Resource
    private IBaseDAO<InstoreDetailsEntity> instoreDetailsDAO;

    @Resource
    private IBaseDAO<InstoreSheetEntity> instoreSheetDAO;

    @Override
    public List<InstoreDetailsEntity> findAllInstoreDetails() {
        return instoreDetailsDAO.find("from InstoreDetailsEntity");
    }

    @Override
    public List<InstoreSheetEntity> findAllInstoreSheet() {
        return instoreSheetDAO.find("from InstoreSheetEntity ins order by ins.dateAt desc");
    }

    @Override
    public Integer findIdByTime(Timestamp time) {
        List<Object> list = new ArrayList<Object>();
        list.add(time);
        return (Integer) instoreSheetDAO.findElement("select ins.instoreSheetId from InstoreSheetEntity ins where dateAt =?",list);
    }

    @Override
    public Timestamp findLatestTime() {
        return (Timestamp) instoreSheetDAO.findElement("select max(ins.dateAt) from InstoreSheetEntity ins");
    }

    @Override
    public List<InstoreDetailsEntity> findInstoreDetailsByPage(int page, int rows) {
        List<Object> list = null;
        return instoreDetailsDAO.find("from InstoreDetailsEntity",list,page,rows);
    }

    @Override
    public List<InstoreSheetEntity> findInstoreSheetByPage(int page, int rows) {
        List<Object> list = null;
        return instoreSheetDAO.find("from InstoreSheetEntity ins order by ins.dateAt desc",list,page,rows);
    }

    @Override
    public List<InstoreDetailsEntity> findInstoreDetailsById(Integer instoreSheetId) {
        List<Object> list = new ArrayList<Object>();
        list.add(instoreSheetId);
        return instoreDetailsDAO.find("from InstoreDetailsEntity where instoreSheetId =?", list);
    }

    @Override
    public List<InstoreDetailsEntity> findInstoreDetailsByProductId(Integer productId) {
        List<Object> list = new ArrayList<Object>();
        list.add(productId);
        return instoreDetailsDAO.find("from InstoreDetailsEntity where productId =?", list);
    }

    @Override
    public InstoreDetailsEntity findInstoreDetailsByPK(Integer instoreSheetId, Integer productId) {
        List<Object> list = new ArrayList<Object>();
        list.add(instoreSheetId);
        list.add(productId);
        return instoreDetailsDAO.get("from InstoreDetailsEntity where instoreSheetId =? and productId =?", list);
    }

    @Override
    public InstoreSheetEntity findInstoreSheetById(Integer id) {
        List<Object> list = new ArrayList<Object>();
        list.add(id);
        return instoreSheetDAO.get("from InstoreSheetEntity where instoreSheetId =?", list);
    }

    @Override
    public List<InstoreSheetEntity> findInstoreSheetByChecker(String checker) {
        List<Object> list = new ArrayList<Object>();
        list.add(checker);
        return instoreSheetDAO.find("from InstoreSheetEntity where checker =?", list);
    }

    @Override
    public List<InstoreSheetEntity> findInstoreSheetByDept(String department) {
        List<Object> list = new ArrayList<Object>();
        list.add(department);
        return instoreSheetDAO.find("from InstoreSheetEntity where department =?", list);
    }

    @Override
    public List<InstoreSheetEntity> findInstoreSheetByTime(Timestamp start, Timestamp end) {
        List<Object> list = new ArrayList<Object>();
        list.add(start);
        list.add(end);
        return instoreSheetDAO.find("from InstoreSheetEntity ins where ins.dateAt between ? and ?",list);
    }

    @Override
    public void add(InstoreDetailsEntity instoreDetails) {
        instoreDetailsDAO.save(instoreDetails);
    }

    @Override
    public void add(InstoreSheetEntity instoreSheet) {
        instoreSheetDAO.save(instoreSheet);
    }

    @Override
    public void delete(InstoreDetailsEntity instoreDetails) {
        instoreDetailsDAO.delete(instoreDetails);
    }

    @Override
    public void delete(InstoreSheetEntity instoreSheet) {
        instoreSheetDAO.delete(instoreSheet);
    }

    @Override
    public void update(InstoreDetailsEntity instoreDetails) {
        instoreDetailsDAO.update(instoreDetails);
    }

    @Override
    public void update(InstoreSheetEntity instoreSheet) {
        instoreSheetDAO.update(instoreSheet);
    }
}

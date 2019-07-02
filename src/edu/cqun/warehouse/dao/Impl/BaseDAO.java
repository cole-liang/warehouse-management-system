package edu.cqun.warehouse.dao.Impl;

import edu.cqun.warehouse.dao.IBaseDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hp on 2015/4/29.
 */

@Repository("baseDAO")
@SuppressWarnings("all")
public class BaseDAO<T> implements IBaseDAO<T> {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession(){
        return  sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(T o) {
        return this.getCurrentSession().save(o);
    }

    @Override
    public void delete(T o) {
        this.getCurrentSession().delete(o);
    }

    @Override
    public void update(T o) {
        this.getCurrentSession().update(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        this.getCurrentSession().saveOrUpdate(o);
    }

    @Override
    public Object findElement(String hql) {
        return this.getCurrentSession().createQuery(hql).list().get(0);
    }

    @Override
    public Object findElement(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0){
            for (int i = 0; i < param.size() ; i++){
                q.setParameter(i, param.get(i));
            }
        }
        return q.list().get(0);
    }

    @Override
    public List<T> find(String hql) {
        return this.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<T> find(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0){
            for (int i = 0; i < param.length ; i++){
                q.setParameter(i, param[i]);
            }
        }
        return q.list();
    }

    @Override
    public List<T> find(String hql, List<Object> param) {
        Session session = this.getCurrentSession();
        Query q = session.createQuery(hql);
        if( param != null && param.size() > 0 ){
            for (int i = 0; i < param.size(); i++){
                q.setParameter(i, param.get(i));
            }
        }
        return q.list();
    }

    @Override
    public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
        if ( page == null || page < 1){
            page = 1;
        }
        if ( rows == null || rows < 1){
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if ( param != null && param.length > 0){
            for ( int i = 0; i < param.length; i++){
                q.setParameter( i, param[i]);
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
        if ( page == null || page < 1 ){
            page = 1;
        }
        if ( rows == null || rows < 1){
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if( param != null && param.size() > 0){
            for (int i = 0; i < param.size(); i++){
                q.setParameter(i, param.get(i));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get( c,id);
    }

    @Override
    public T get(String hql, Object[] param) {
        List<T> list = this.find(hql, param);
        if ( param != null && param.length > 0 && list.size() != 0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public T get(String hql, List<Object> param) {
        List<T> list = this.find(hql, param);
        if ( param != null && param.size() > 0 && list.size() != 0){
            return list.get(0);
        }else {
            return null;
        }
    }
}

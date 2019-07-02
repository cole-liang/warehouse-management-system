package edu.cqun.warehouse.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by hp on 2015/4/29.
 */
public interface IBaseDAO<T> {

    /**
     * 保存一个对象
     * @param o
     */
    public Serializable save(T o);

    /**
     * 删除一个对象
     * @param o
     */
    public void delete(T o);

    /**
     * 更新一个对象
     * @param o
     */
    public void update(T o);

    /**
     * 保存或更新一个对象（无法判断是否为游离态时用）
     * @param o
     */
    public void saveOrUpdate(T o);

    /**
     * 查找元素
     * @param hql
     * @return
     */
    public Object findElement(String hql);

    /**
     * 条件查找元素
     * @param hql
     * @param param
     * @return
     */
    public Object findElement(String hql, List<Object> param);

    /**
     * 查找
     * @param hql
     * @return
     */
    public List<T> find(String hql);

    /**
     * 条件查找
     * @param hql
     * @param param
     * @return
     */
    public List<T> find(String hql, Object[] param);

    /**
     * 条件查找
     * @param hql
     * @param param
     * @return
     */
    public List<T> find(String hql, List<Object> param);

    /**
     * 带分页的条件查找
     * @param hql
     * @param param
     * @param page
     *           查询第几页
     * @param rows
     *           每页显示几条记录
     * @return
     */
    public List<T> find(String hql, Object[] param, Integer page, Integer rows);

    /**
     * 带分页的条件查询
     * @param hql
     * @param param
     * @param page
     * @param rows
     * @return
     */
    public List<T> find(String hql, List<Object> param, Integer page, Integer rows);

    /**
     * 获得一个对象
     * @param c
     * @param id
     * @return
     */
    public T get(Class<T> c, Serializable id);

    /**
     * 通过条件获得一个对象
     * @param hql
     * @param param
     * @return
     */
    public T get(String hql, Object[] param);

    /**
     * 通过条件获得一个对象
     * @param hql
     * @param param
     * @return
     */
    public T get(String hql, List<Object> param);



}

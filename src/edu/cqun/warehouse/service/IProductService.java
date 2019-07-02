package edu.cqun.warehouse.service;

import edu.cqun.warehouse.entity.ProductEntity;

import java.util.List;

public interface IProductService {

    /**
     * 按页码找材料
     * @param page
     * @param rows
     * @return
     */
    public List<ProductEntity> findByPage(int page, int rows);

    /**
     * 查找全部材料信息
     * @return
     */
    public List<ProductEntity> findAllProduct();

    /**
     * 根据编号找材料
     * @param id
     * @return
     */
    public ProductEntity findById(Integer id);

    /**
     * 根据名字找材料
     * @param name
     * @return
     */
    public List<ProductEntity> findByName(String name);

    /**
     * 添加材料信息
     * @param product
     */
    public void add(ProductEntity product);

    /**
     * 删除材料信息
     * @param product
     */
    public void delete(ProductEntity product);

    /**
     * 更新材料信息
     * @param product
     */
    public void update(ProductEntity product);
}

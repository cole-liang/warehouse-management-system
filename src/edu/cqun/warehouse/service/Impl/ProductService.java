package edu.cqun.warehouse.service.Impl;

import edu.cqun.warehouse.dao.IBaseDAO;
import edu.cqun.warehouse.entity.ProductEntity;
import edu.cqun.warehouse.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("productService")
public class ProductService implements IProductService{

    @Resource
    private IBaseDAO<ProductEntity> productDAO;

    @Override
    public List<ProductEntity> findByPage(int page, int rows) {
        return null;
    }

    @Override
    public List<ProductEntity> findAllProduct() {
        return productDAO.find("from ProductEntity");
    }

    @Override
    public ProductEntity findById(Integer id) {
        List<Object> list = new ArrayList<Object>();
        list.add(id);
        return productDAO.get("from ProductEntity where productId =?",list);
    }

    @Override
    public List<ProductEntity> findByName(String name) {
        List<Object> list = new ArrayList<Object>();
        list.add(name);
        return productDAO.find("from ProductEntity where productName =?",list);
    }

    @Override
    public void add(ProductEntity product) {
        productDAO.save(product);
    }

    @Override
    public void delete(ProductEntity product) {
        productDAO.delete(product);
    }

    @Override
    public void update(ProductEntity product) {
        productDAO.update(product);
    }
}

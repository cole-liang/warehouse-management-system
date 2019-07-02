package edu.cqun.warehouse.service.Impl;

import edu.cqun.warehouse.dao.IBaseDAO;
import edu.cqun.warehouse.entity.UserEntity;
import edu.cqun.warehouse.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("userService")
public class UserService implements IUserService{

    @Resource
    private IBaseDAO<UserEntity> userDAO;

    @Override
    public void addUser(UserEntity user) {

    }

    @Override
    public UserEntity findByName(String name) {
        List<Object> list = new ArrayList<Object>();
        list.add(name);
        return userDAO.get("from UserEntity where userName =?", list);
    }
}

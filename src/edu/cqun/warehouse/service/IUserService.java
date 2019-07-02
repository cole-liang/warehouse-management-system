package edu.cqun.warehouse.service;

import edu.cqun.warehouse.entity.UserEntity;

public interface IUserService {

    /**
     * 添加用户
     * @param user
     */
    public void addUser(UserEntity user);

    /**
     * 通过用户名寻找
     * @param name
     * @return
     */
    public UserEntity findByName(String name);
}

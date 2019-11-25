package com.lsm.user.dao;


import com.lsm.user.entity.UserEntity;

public interface IUserDao {
    void saveJDBC(UserEntity userEntity);

    void removeJDBC(Integer id);

    void updateJDBC(UserEntity userEntity);

    UserEntity listJDBC();
}

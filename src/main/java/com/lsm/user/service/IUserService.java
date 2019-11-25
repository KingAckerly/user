package com.lsm.user.service;


import com.lsm.user.entity.UserEntity;

public interface IUserService {
    void saveJDBC(UserEntity userEntity);

    void removeJDBC(Integer id);

    void updateJDBC(UserEntity userEntity);

    UserEntity listJDBC();

    void getDruidMonitorData();
}

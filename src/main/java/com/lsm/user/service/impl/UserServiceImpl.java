package com.lsm.user.service.impl;


import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.lsm.user.dao.IUserDao;
import com.lsm.user.entity.UserEntity;
import com.lsm.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    IUserDao userDao;


    @Override
    public void saveJDBC(UserEntity userEntity) {
        userDao.saveJDBC(userEntity);
    }

    @Override
    public void removeJDBC(Integer id) {
        userDao.removeJDBC(id);
    }

    @Override
    public void updateJDBC(UserEntity userEntity) {
        userDao.updateJDBC(userEntity);
    }

    @Override
    public UserEntity listJDBC() {
        return userDao.listJDBC();
    }

    @Override
    public void getDruidMonitorData() {
        //获取Druid监控数据
        List<Map<String, Object>> map = DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
        System.out.println(map);
    }
}

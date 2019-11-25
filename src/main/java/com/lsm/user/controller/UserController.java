package com.lsm.user.controller;

import com.lsm.user.entity.UserEntity;
import com.lsm.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Value("${server.port}")
    private Integer port;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "登录成功,端口号是：" + port;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestHeader(value = "foo") String foo) {
        System.out.println(foo);
        return "test";
    }

    @RequestMapping(value = "/saveJDBC", method = RequestMethod.POST)
    public String saveJDBC(@RequestBody UserEntity userEntity) {
        userService.saveJDBC(userEntity);
        return "SUCCESS";
    }

    @RequestMapping(value = "/removeJDBC", method = RequestMethod.POST)
    public String removeJDBC(@RequestBody UserEntity userEntity) {
        userService.removeJDBC(userEntity.getId());
        return "SUCCESS";
    }

    @RequestMapping(value = "/updateJDBC", method = RequestMethod.POST)
    public String updateJDBC(@RequestBody UserEntity userEntity) {
        userService.updateJDBC(userEntity);
        return "SUCCESS";
    }

    @RequestMapping(value = "/listJDBC", method = RequestMethod.GET)
    public String listJDBC() {
        userService.listJDBC();
        return "SUCCESS";
    }

    @RequestMapping(value = "/getDruidMonitorData", method = RequestMethod.GET)
    public String getDruidMonitorData() {
        userService.getDruidMonitorData();
        return "SUCCESS";
    }

}

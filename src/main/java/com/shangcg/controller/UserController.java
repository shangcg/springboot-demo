package com.shangcg.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shangcg.pojo.User;
import com.shangcg.service.IUserService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    //http://localhost:8080/showUser?userId=1     测试mybatis是否连接正常

    @Resource
    private IUserService userService;

    @RequestMapping("/showUser")
    public User getUser(@RequestParam(value = "userId", defaultValue = "0") Integer userId) {
        try {
            User user = this.userService.getUserById(userId);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/insert_user", method = RequestMethod.POST)
    public void getUser() {
        try {
            userService.BatchInsertData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

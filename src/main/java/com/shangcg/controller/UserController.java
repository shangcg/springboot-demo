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

    //http://localhost:8080/showUser?userId=8     测试mybatis是否连接正常

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

    //http://localhost:8080/hello.json?name=shangcg    测试springboot是否正常启动

    @RequestMapping(value = "/hello.json", method = RequestMethod.GET)
    public String getListTag(HttpServletRequest request,
                             @RequestParam(value = "name", required = false, defaultValue = "0") String name) {
        try {
            return "hello :" + name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello everyone !";
    }



}

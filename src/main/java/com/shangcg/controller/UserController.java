package com.shangcg.controller;

import javax.annotation.Resource;
import com.shangcg.pojo.User;
import com.shangcg.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    //http://localhost:8080/showUser?userId=55     测试mybatis是否连接正常

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




}

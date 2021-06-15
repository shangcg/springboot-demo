package com.shangcg.controller;

import com.shangcg.service.RedisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 针对redis缓存做的测试
 */

@RestController
public class RedisController {

    @Resource
    private RedisService redisService;


    @RequestMapping("/redis/aop")
    public String greeting(@RequestParam(value="key") String key) {
        return redisService.addUser(key);
    }
}

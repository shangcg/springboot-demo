package com.shangcg.service.impl;

import com.shangcg.service.RedisService;
import org.springframework.stereotype.Service;
import com.shangcg.redis.aop.CacheAnno;

@Service
public class RedisServiceImpl implements RedisService {

    @CacheAnno
    @Override
    public String addUser(String name) {
        System.out.println("aa");
        return "aad";
    }
}

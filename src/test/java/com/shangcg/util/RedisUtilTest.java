package com.shangcg.util;

import javax.annotation.Resource;
import com.shangcg.base.RootTest;
import org.junit.Test;

/**
 * @version v1.0
 * @Description: TODO
 * @Author: shangcg
 * @Date: 2019/12/26
 */


public class RedisUtilTest extends RootTest {

    @Resource
    private RedisUtil redisUtil;


    @Test
    public void get() {
    }

    @Test
    public void set() {
        redisUtil.set("redis_key", "redis_vale");
    }

    @Test
    public void getAndSet() {
        String value = redisUtil.get("redis_key");
        System.out.println(value);
    }

    @Test
    public void delete() {

    }
}
package com.shangcg.controller;


import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HelloController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final RateLimiter rateLimiter = RateLimiter.create(2);

    /**
     * tryAcquire尝试获取permit，默认超时时间是0，意思是拿不到就立即返回false
     */
    @RequestMapping("/sayHello")
    public String sayHello() {
        if (rateLimiter.tryAcquire()) { //  一次拿1个
            System.out.println(sdf.format(new Date()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("limit");
        }
        return "hello";
    }

    /**
     * acquire拿不到就等待，拿到为止
     */
    @RequestMapping("/sayHi")
    public String sayHi() {
        rateLimiter.acquire(5); //  一次拿5个
        System.out.println(sdf.format(new Date()));
        return "hi";
    }

}
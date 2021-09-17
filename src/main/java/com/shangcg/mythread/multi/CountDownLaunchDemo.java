package com.shangcg.mythread.multi;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 闭锁使用场景：
 * 1 某一线程在开始执行之前，需要等待n个线程执行完毕，可以考虑用闭锁
 * 2 多个线程需要同时执行时，可以用闭锁创建new countDownLaunch（1）, 然后把所有线程都创建好，且每个线程都调用await()等待在countDownLaunch上，
 *   之后由主线程调用countDown.countDown()将计数器归零， 所有等待在该countDownLaunch的线程被唤醒达到同时执行的效果
 *
 * 闭锁使用方法：
 * 1 创建countDownLaunch、并指定主线程需要等待的线程数量
 * 2 每当子线程执行完毕就调用 countDown.countDown()方法，该方法是将计数器数量-1
 * 3 主线程调用countDownLaunch.await（）方法一直等待，当计数器减少为0时主线程会被唤醒
 */
public class CountDownLaunchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        Long begin = System.currentTimeMillis();

        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            }).start();
        }

        System.out.println("主线程还在运行");

        //调用await会阻塞主线程，直到countDown计数器变为0会被唤醒
        countDownLatch.await(100, TimeUnit.SECONDS);

        //主线程继续执行
        System.out.println("子线程运行结束");


    }



}


class Child{

}

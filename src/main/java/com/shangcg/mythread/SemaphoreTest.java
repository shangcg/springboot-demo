package com.shangcg.mythread;

import java.util.concurrent.Semaphore;

/**
 * 信号量测试
 * 信号量常用于限流
 * 可理解为停车场指示牌，初始化信号10(总共10个车位)， semaphore.acquire相当于进入一辆车，调用acquire则信号量-1，
 *          semaphore.release()相当于出去一辆车，释放掉一个信号量
 *
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 20; i++){
            new Thread(() ->{
                try {
                    //
                    if (semaphore.availablePermits() ==0){
                        //没有可获取的信号量，等待
                        System.out.println("信号量不足，请耐心等待");
                    }
                    //尝试获取信号量令牌
                    semaphore.acquire();
                    System.out.println("获取令牌成功");
                    Thread.sleep(100);//模拟停车
                    semaphore.release(); //释放令牌，车辆开出
                    System.out.println("释放令牌，出车");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}

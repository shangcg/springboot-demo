package com.shangcg.mythread.multi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程卖票经典案例，不加锁导致数据混乱，利用Lock实现多线程的控制
 *
 * 锁是什么：
 * 如何判断锁的是谁:
 *
 * Lock 和synchronized的却别
 * 1： synchronized 是内置java关键字、 Lock是一个接口
 * 2： Synchronized 无法获取到锁的状态、lock可以获取到锁的状态
 * 3： synchronized 是全自动的，  lock需要手动释放锁
 * 4： synchronized 会傻傻的等待，lock可以尝试获取锁
 * 5； synchronized 可重入，不可以中断，非公平； lock可重入锁，可以设置公平非公平，可中断
 * 6: synchronized 适合锁小段代码， lock适合大段代码
 */

public class LockTest {


    public static void main(String args[]){

        Ticket1 ticket1 = new Ticket1();
        new Thread(() ->{
            for (int i = 0; i < 60; i++){
                ticket1.sale();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 60; i++){
                ticket1.sale();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

    }

}


class Ticket1{

    private int number = 50;

    Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        if(number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + number-- + "剩余" + number);
        }
        lock.unlock();
    }

}
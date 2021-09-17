package com.shangcg.mythread;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印
 *
 * 用synchronized + wait + notify 实现 控制两个线程交替获取到所打印
 *
 *
 * @author shangchenguang
 * @date 2021/8/26 11:09 上午
 */
public class TwoThreadPrint3 {

    Object block = new Object();


    public static void main(String[] args) {
        TwoThreadPrint3 print3 = new TwoThreadPrint3();
        System.out.println(print3);

        print3.t1.start();
        print3.t2.start();
    }


    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                synchronized (this){
                    System.out.println(this);
                    System.out.println(this.getClass());
                    System.out.println(Arrays.toString(this.getClass().getInterfaces()));

                    this.notify();
                    System.out.println(Thread.currentThread().getName() + "1");
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    });


    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                synchronized (this){
                    System.out.println(this);
                    System.out.println(this.getClass());
                    System.out.println(Arrays.toString(this.getClass().getInterfaces()));

                    this.notify();
                    System.out.println(Thread.currentThread().getName() + "2");
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    });
}

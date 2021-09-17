package com.shangcg.mythread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印
 *
 * 用lock + flag 实现 控制两个线程交替获取到所打印
 *
 * @author shangchenguang
 * @date 2021/8/26 11:09 上午
 */
public class TwoThreadPrint {

    boolean flag = true;

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        TwoThreadPrint twoThreadPrint = new TwoThreadPrint();
        twoThreadPrint.thread1.start();
        twoThreadPrint.thread2.start();
    }


    Thread thread1 = new Thread(new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
            while (true){
                lock.lock();
                if (flag){
                    System.out.println(Thread.currentThread().getName() + "我是线程1");
                    Thread.sleep(1000);
                    flag = false;
                }
                lock.unlock();
            }

        }
    });


    Thread thread2 = new Thread(new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
            while (true){
                lock.lock();
                if (!flag){
                    System.out.println(Thread.currentThread().getName() + "我是线程2");
                    Thread.sleep(1000);
                    flag = true;
                }
                lock.unlock();
            }
        }
    });

}

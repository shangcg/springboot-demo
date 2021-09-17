package com.shangcg.mythread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印
 *
 * 用lock + condition + signal + await 实现 控制两个线程交替获取到所打印
 *
 * 线程1 获取到lock后，就唤醒所有等待在condition中的线程，自己输出，然后释放掉锁进入await状态， 其他线程早已被唤醒等待锁，获取锁执行输出，然后释放锁处于await
 *
 * @author shangchenguang
 * @date 2021/8/26 11:09 上午
 */
public class TwoThreadPrint2 {

    public static void main(String[] args) {
        TwoThreadPrint2 twoThreadPrint2 = new TwoThreadPrint2();
        twoThreadPrint2.thread1.start();
        twoThreadPrint2.t2.start();
    }

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                lock.lock();
                condition.signal();
                System.out.println(thread1.getName() + "0");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    });



    Thread t2 = new Thread(new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
            while (true){
                lock.lock();
                condition.signal();
                System.out.println(thread1.getName() + "1");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }

        }
    });

}

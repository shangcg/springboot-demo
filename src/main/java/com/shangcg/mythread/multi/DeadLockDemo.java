package com.shangcg.mythread.multi;

import lombok.SneakyThrows;

/**
 * 死锁案例
 *
 * @author shangchenguang
 * @date 2021/7/25 11:09 上午
 */
public class DeadLockDemo {

    private static  String A = "A";
    private static  String B = "B";

    private void deadLock(){
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
}

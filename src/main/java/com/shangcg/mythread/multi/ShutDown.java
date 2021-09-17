package com.shangcg.mythread.multi;

import java.util.concurrent.TimeUnit;

/**
 * 线程的中断操作  安全地终止线程
 * 通过标识位或者中断地方式，使得线程在终止时有机会清理资源
 *
 * @author shangchenguang
 * @date 2021/8/2 8:10 下午
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {

        Runner one = new Runner();
        Thread t1 = new Thread(one, "CountThread");
        t1.start();

        //休眠1s、main线程对t1线程进行中断、使得t1线程能够感知到中断而结束
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();

        Runner two = new Runner();
        t1 = new Thread(two, "CountThread");
        t1.start();
        //休眠1s、main线程对runner two进行取消，使CountThread能够感知on为false而技术
        TimeUnit.SECONDS.sleep(1);
        two.cancel();

    }

    static class Runner implements Runnable{

        private long i;
        private volatile boolean on  = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel(){
            on = false;
        }

    }
}

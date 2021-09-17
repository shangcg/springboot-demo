package com.shangcg.mythread.multi;

import java.util.concurrent.TimeUnit;

/**
 * 测试线程中断
 * 该类用来证明： 许多声明抛出isInterrupted 的方法，在方法抛出interrupted异常之前，会将异常标志为清楚
 * SleepRunner为休眠线程、强行被其他线程中断会抛出interrupted异常，同时也会清楚中断标志位，所以sleepThread 输出为false
 *
 * @author shangchenguang
 * @date 2021/8/2 12:38 下午
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(), "sleepRunner");
        Thread busyThread = new Thread(new BusyRunner(), "busyRunner");

        sleepThread.setDaemon(true);
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        //休眠 让线程充分运行
        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleep thread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busy thread interrupted is " + busyThread.isInterrupted());

        //防止线程立即退出
        ThreadState.SleepUtils.second(2);

    }

    //不停睡眠的线程
    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            while (true){
                ThreadState.SleepUtils.second(10);
            }
        }
    }

    //一直运行的线程
    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while (true){

            }
        }
    }
}

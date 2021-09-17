package com.shangcg.mythread.multi;

import java.lang.management.ThreadInfo;
import java.util.concurrent.TimeUnit;

/**
 * 线程状态测试
 * 通过jstack工具查看
 *
 * @author shangchenguang
 * @date 2021/8/1 5:36 下午
 */
public class ThreadState {


    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();

        new Thread(new Blocked(), "BlockedThread - 1").start();
        new Thread(new Blocked(), "BlockedThread - 2").start();
    }

    //休眠状态线程 该线程不断进行休眠
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(100);
            }
        }
    }

    //等待状态线程、 该线程在waiting.class实例上等待
    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    }catch (InterruptedException e){

                    }
                }
            }
        }
    }

    //阻塞线程，该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }


    //休眠工具类
    static class SleepUtils{
        public static final void second(long seconds){
            try {
                TimeUnit.SECONDS.sleep(seconds);
            }catch (InterruptedException e){

            }
        }
    }
}

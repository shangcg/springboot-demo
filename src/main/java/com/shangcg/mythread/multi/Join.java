package com.shangcg.mythread.multi;

import java.util.concurrent.TimeUnit;

/**
 * Thread.join()测试
 *
 * 是这个样子的： for循环创建10个线程， 首先是主线程main 创建了0号线程，0号线程被join挂起，
 * 此时main线程不结束，则0号线程结束不了，因为0号线程调用了join方法不能从join方法返回
 *
 * @author shangchenguang
 * @date 2021/8/7 6:25 下午
 */
public class Join {


    /**
     * 是
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        //当前线程
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++){
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
    }

    // 该类中调用了join（）
    static class Domino implements Runnable{

        private Thread thread;
        public Domino(Thread thread){
            thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            }catch (InterruptedException e){

            }
            System.out.println(Thread.currentThread().getName() + "terminate");
        }
    }
}

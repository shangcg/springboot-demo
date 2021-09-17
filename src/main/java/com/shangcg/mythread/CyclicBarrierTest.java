package com.shangcg.mythread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierTest
 * 屏障类测试，与 闭锁CountDownLatch相反，是一个加法计数器， 闭锁是一个减法计数器
 * 当
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->{
            System.out.println("计数器到7了，轮到我执行了");
        });

        for (int i = 0; i < 7; i++){
            int finalI = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + finalI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}

package com.shangcg.mythread.multi;

/**
 * 多线程与单线程效率对比
 * 多线程累加 与 单线程串行执行效率对比
 *
 * @author shangchenguang
 * @date 2021/7/24 12:27 下午
 */
public class ConcurrencyTest {

    private static final Long count = 10000L;

    //一个方法中两个线程、一个线程执行累加 一个线程执行自减
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < count; i++){
                    a += a;
                }
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < count; i++){
            b--;
        }
        thread.join();
        long end = System.currentTimeMillis();
        System.out.println(end -start);
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++){
            a += a;
        }

        int b = 0;
        for (int i = 0; i < count; i++){
            b--;
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //十个亿的结果： 709 1349 多线程快于串行执行 并行比串行快了1倍
    //一百万的结果： 6   6  执行效率差不多
    //一万的结果    5   1  串行比并行快了5倍
    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }
}

package com.shangcg.jvm;


import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.concurrent.*;

/**
 * 测试 线程 是否 会退出
 */
public class TestBreak {

    public void test(){
        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                r -> new Thread(r, "a" + r.hashCode()),
                new ThreadPoolExecutor.CallerRunsPolicy());


        CompletableFuture.runAsync(() -> {
            try {
                if (true){
                    throw new IOException();
                }
                throw new ConcurrentModificationException("aaaa");
            }catch (IOException e){
                System.out.println("abc");
            }
        });
    }

    public static void main(String[] args) {
        TestBreak testBreak = new TestBreak();
        testBreak.test();
    }

}

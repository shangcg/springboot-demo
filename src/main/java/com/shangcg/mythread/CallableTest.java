package com.shangcg.mythread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程之 CallableTest
 * callable的特点： 1可以有返回值 2可以抛异常
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread myThread = new MyThread();
        FutureTask<String> futureTask = new FutureTask<String>(myThread);

        new Thread(futureTask, "A").start();

        System.out.println(futureTask.get());

    }



}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "hello";
    }
}

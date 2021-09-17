package com.shangcg.mythread;

import java.util.concurrent.*;

/**
 * callableTest2
 *
 */
public class CallableTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        TaskThread t = new TaskThread("");
        Future<String> aa = executorService.submit(new TaskThread("aa"));
        System.out.println("输出结果为：" + aa.get());
    }

}

class TaskThread implements Callable<String>{
    private String t ;
    public TaskThread(String temp){
        t = temp;
    }

    @Override
    public String call() throws Exception {
        return "hello";
    }
}

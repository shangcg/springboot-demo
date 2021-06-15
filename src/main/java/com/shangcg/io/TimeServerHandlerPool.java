package com.shangcg.io;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * socket通信 server端 采用线程池处理客户端连接
 */
public class TimeServerHandlerPool {
    private ExecutorService executorService;

    public TimeServerHandlerPool(int poolSize, int queueSize){
        executorService = new ThreadPoolExecutor(8, poolSize, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable task){
        executorService.execute(task);
    }
}

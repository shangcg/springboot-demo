package com.shangcg.mythread.multi;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal使用测试
 *
 * @author shangchenguang
 * @date 2021/8/8 8:32 上午
 */
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost" + Profiler.end() + "mills");
    }

}

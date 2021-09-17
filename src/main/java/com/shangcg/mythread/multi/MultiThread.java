package com.shangcg.mythread.multi;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 启动一个main方法，查询启动多少线程
 *
 * @author shangchenguang
 * @date 2021/8/1 5:29 下午
 */
public class MultiThread {

    public static void main(String[] args) {
        //获取java线程管理Mxbean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //获取线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        //遍历线程堆栈信息
        for (ThreadInfo threadInfo: threadInfos){
            System.out.println(threadInfo.getThreadId() + threadInfo.getThreadName());
        }

    }
}

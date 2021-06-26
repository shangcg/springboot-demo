package com.shangcg.mythread.mesa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 该类不太好。。。。。看下一个MyQueen
 * 管程模型：
 * 并发需要解决的问题： 1互斥 2同步(通信)  通常有两种手段解决 1信号量原语 2管程模型 管理解为管理，程理解为过程，不知道这样理解对不对，反正是通俗
 * 什么是管程模型：管理共享变量以及对共享变量的操作过程，让他们支持并发，这就是管程
 * 管程模型解决了 互斥、同步问题
 * 管程模型构成要素：？
 *
 * @param <T>
 */
public class BlockQueueTest<T> {
    final Lock lock = new ReentrantLock();

    //条件队列：队列不满
    final Condition notFull = lock.newCondition();

    //条件队列：队列不空
    final Condition notEmpty = lock.newCondition();

    //队列已满 此处需根据实际定义？
    boolean full = true;

    //队列已空 此处需根据实际定义？
    boolean empty = true;

    //入队操作
    void enq(T x){
        lock.lock();
        try {
            while (full){
                //等待队列不满
                notFull.await();
            }
            //省略入队操作。。。
            //入队后，可通知出队
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //出队
    void deq() throws InterruptedException {
        lock.lock();
        try {
            while (empty){
                //等待队列不空
                notEmpty.await();
            }
            //省略出队操作
            //出队后，通知可入队
            notFull.signal();
        }finally {
            lock.unlock();
        }
    }
}

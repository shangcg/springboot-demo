package com.shangcg.mythread.mesa;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 管程模型 并发队列  https://blog.csdn.net/death05/article/details/94177146
 */
public class MyQueen {
    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    private final LinkedList<Integer> linkedList = new LinkedList<>();

    private int capacity;

    private int size;




}


package com.shangcg.mythread.producer;


import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程A、B、C A执行完后通知B、B执行完后通知C
 *
 * A执行完调用B  B执行完调用C、 C执行完调用A
 *
 *
 */
public class ConditionTest {

    public static void main(String[] args) {

        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10 ; i++){
                data.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10 ; i++){
                data.printB();
            }
        }, "B").start();

        new Thread(() ->{
            for (int i = 0; i < 10 ; i++){
                data.printC();
            }
        }, "C").start();
    }

}


class Data{

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int number = 1;

    public void printA(){
        lock.lock();
        try {
            while (number != 1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "A");
            //唤醒指定的线程
            number = 2;
            condition2.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void printB(){
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "B");
            number = 3;
            condition3.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "C");
            number = 1;
            condition1.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}

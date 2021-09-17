package com.shangcg.mythread.producer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**（
 * 通过lock condition实现多个线程通信
 */
public class ProducerLock {

    public static void main(String[] args) {
        Resource1 resource = new Resource1();

        new Thread(() ->{
            for (int i = 0; i < 1000; i++){
                resource.increment();
            }
        }, "A").start();


        new Thread(() ->{
                for (int i = 0; i < 1000; i++){
                   resource.decrement();
                }
        }, "B").start();

        new Thread(() ->{
                for (int i = 0; i < 1000; i++){
                    resource.increment();
                }
        }, "C").start();


        new Thread(() ->{
                for (int i = 0; i < 1000; i++){
                      resource.decrement();

                }

        }, "D").start();



    }
}


//资源类、与多线程调用方隔离
class Resource1{

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private int number = 0;
    public void increment() {
        //当number = 0时 + 1,否则等待
        lock.lock();
        try {
            //此处如果有多个线程在wait中， 相当于唤醒了多个线程， 但是当某一个线程被唤醒执行后，另一个线程就不满足条件了，这就是虚假唤醒，解决虚假唤醒的手段： 在while中进行wait操作，在线程被唤醒后，会重新进行条件判断，而不是直接执行wait后边的代码
            while (number != 0){
                condition.await();
            }
            number ++;
            System.out.println(Thread.currentThread().getName() + " " + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrement(){
        //当number ！= 0时 -1 否则等待

        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            number --;
            System.out.println(Thread.currentThread().getName()+ " " +number);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}




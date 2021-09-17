package com.shangcg.mythread.producer;

/**
 * 利用锁实现生产者消费者模型  实现线程间通信
 *
 * 两个线程交替执行， A线程当number = 0时 + 1，通知其他线程,当其他线程不等0时  -1
 *
 * 如果时4个线程 A B C D还安全吗   下边资源类有虚假唤醒问题
 * 如果有多个线程在wait中， 相当于唤醒了多个线程， 但是当某一个线程被唤醒执行后，另一个线程就不满足条件了，这就是虚假唤醒，解决虚假唤醒的手段： 在while中进行wait操作，在线程被唤醒后，会重新进行条件判断，而不是直接执行wait后边的代码
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {
        Resource resource = new Resource();

        new Thread(() ->{
            try {
                for (int i = 0; i < 1000; i++){
                    resource.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();


        new Thread(() ->{
            try {
                for (int i = 0; i < 1000; i++){
                    resource.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() ->{
            try {
                for (int i = 0; i < 1000; i++){
                    resource.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();


        new Thread(() ->{
            try {
                for (int i = 0; i < 1000; i++){
                    resource.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();

    }
}



//资源类、与多线程调用方隔离
class Resource{

    private int number = 0;


    public synchronized void increment() throws InterruptedException {
        //当number = 0时 + 1,否则等待

        //此处如果有多个线程在wait中， 相当于唤醒了多个线程， 但是当某一个线程被唤醒执行后，另一个线程就不满足条件了，这就是虚假唤醒，解决虚假唤醒的手段： 在while中进行wait操作，在线程被唤醒后，会重新进行条件判断，而不是直接执行wait后边的代码
        while (number != 0){
            this.wait();
        }
        number ++;
        System.out.println(Thread.currentThread().getName() + " " + number);
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //当number ！= 0时 -1 否则等待
        while (number == 0){
            this.wait();
        }
        number --;
        System.out.println(Thread.currentThread().getName()+ " " +number);
        notifyAll();
    }

}

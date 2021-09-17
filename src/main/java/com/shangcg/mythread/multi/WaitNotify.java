package com.shangcg.mythread.multi;

/**
 * 线程间的通信机制测试
 * thread A 先获取到对象o的锁，进入代码后发现条件不满足就调用对象o 的wait方法，
 *    o.wait(), 并释放锁， 此时thread A进入等待队列waitQueue,变为等待状态
 * thred B 获取到了对象o的锁，进入程序执行逻辑，执行后调用对象o的notify、或者notifyAll，
 *    o.notify， 此时会将等待队列waitQueue中的线程 thread A移动到同步阻塞队列中去 BlockQueue，
 *    thread A变为blocked状态，然后B线程执行完毕才会释放锁
 * thread A重新获取到对象o的 锁，判断条件是否满足然后执行逻辑
 *
 *
 *
 * 锁是面向使用者的，定义了使用者与锁交互的接口
 * 同步器是面向锁的的，简化了锁的实现细节，屏蔽了同步状态管理，线程的排队、等待、唤醒
 * 锁和同步器很好的隔离了使用者和实现者所需关注的领域
 *
 * @author shangchenguang
 * @date 2021/8/7 5:53 下午
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    static class Wait implements Runnable{

        @Override
        public void run() {
            //代码块加锁、 拥有lock的monitor
            synchronized (lock){
                //当条件不满足时，继续wait，并释放锁
                while (flag){

                    try {
                        System.out.println("条件不满足，flag is true");
                        //继续wait 并释放锁
                        lock.wait();
                    }catch (Exception e){

                    }

                }

                //条件满足开始工作
                System.out.println(Thread.currentThread() + "flag is false 满足条件");

            }
        }
    }


    static class Notify implements Runnable{

        @Override
        public void run() {
            //加锁、拥有lock的monitor、
            synchronized (lock){

            }
        }
    }
}

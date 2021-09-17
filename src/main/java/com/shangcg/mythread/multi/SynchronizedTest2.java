package com.shangcg.mythread.multi;

/**
 * 多线程卖票经典案例，不加锁导致数据混乱，利用synchronized实现多线程的控制
 */
public class SynchronizedTest2 {


    public static void main(String args[]){
        // new Thread(new MyThread().start);

        Ticket ticket = new Ticket();

        new Thread(() ->{
            for (int i = 0; i < 60; i++){
                ticket.sale();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 60; i++){
                ticket.sale();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

//        new Thread(() ->{
//            for (int i = 0; i < 60; i++){
//                ticket.sale();
//            }
//        }, "C").start();
    }

}




class Ticket{

    private int number = 50;

    //此处不加锁会导致超卖
    public synchronized void sale(){
        if(number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + number-- + "剩余" + number);
        }
    }

}
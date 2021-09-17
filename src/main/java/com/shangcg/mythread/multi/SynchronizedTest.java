package com.shangcg.mythread.multi;

/**
 * 线程间通信、 synchronized关键字测试
 *
 * 使用javap 工具查看生成的class文件
 * javap使用方法： 先将.java文件编译成class文件， 之后使用javap -v .class即可查看java字节码
 * @author shangchenguang
 * @date 2021/8/2 8:32 下午
 */
public class SynchronizedTest {

    public static void main(String[] args) {

        //对SynchronizedTest class对象加锁
        synchronized (SynchronizedTest.class){

        }
        m();

    }

    //对静态方法加锁
    public static synchronized void m(){

    }


    /**
     * 字节码如下
     *
     * synchronized修饰的代码块，通过monitorenter、 monitorexit 控制同一时刻只能有一个线程访问
     * synchronized修饰的的方法，通过ACC_SYNCHRONIZED控制同一个时刻只能有一个线程访问
     * 本质： 本质是对一个对象的监视器(monitor)进行获取，这个过程是排他的
     * 任意一个对象都有自己的监视器、当调用这个对象是由同步块或者同步方法调起、则调用者必须获取到该对象的monitor监视器，否则被阻塞
     *
     *
     * public static void main(java.lang.String[]);
     *     descriptor: ([Ljava/lang/String;)V
     *     flags: ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=2, locals=3, args_size=1
     *          0: ldc           #2                  // class com/shangcg/mythread/multi/SynchronizedTest
     *          2: dup
     *          3: astore_1
     *          4: monitorenter
     *          5: aload_1
     *          6: monitorexit
     *          7: goto          15
     *         10: astore_2
     *         11: aload_1
     *         12: monitorexit
     *         13: aload_2
     *         14: athrow
     *         15: invokestatic  #3                  // Method m:()V
     *         18: return
     *
     *
     *
     *       public static synchronized void m();
     *          descriptor: ()V
     *          flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
     *          Code:
     *               stack=0, locals=0, args_size=0
     *               0: return
     *          LineNumberTable:
     *              line 25: 0
     * }
     *
     *
     */

}

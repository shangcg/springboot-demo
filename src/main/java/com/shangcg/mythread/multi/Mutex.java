package com.shangcg.mythread.multi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 队列同步器使用角度分析、自定义队列同步器
 *
 *
 * 自定义同步器组件 mutex是一个独占锁自定义同步器
 * ReentrantLock是自定义同步组件，只不过是jdk自己定义的
 * 此处自己实现一个自定义同步组件，自定义同步组件被推荐定义一个静态内部类,
 *      继承同步器AQS去重写tryAcquire、 release等方法
 *
 * AQS内部维护了一个state状态，来控制线程是否获取到锁
 *
 * @author shangchenguang
 * @date 2021/8/9 7:50 上午
 */
public class Mutex implements Lock {

    //静态内部类、自定一个同步器，需要继承AQS并重写AQS相关方法
    static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean isHeldExclusively() {
            //是否处于独占状态  == 1 说明是处于独占状态
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            // 当状态为0时，说明可以获取到锁
            if (compareAndSetState(0 ,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //state == 0 说明资源没被占用，不用释放锁、抛出异常
            if (getState() == 0){
                throw new IllegalMonitorStateException();
            }

            //state == 1则释放掉锁、将当前占用线程设置为null、state设置为0
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //返回一个Condition、每个condition都包含一个condition队列
        Condition newCondition(){
            return new ConditionObject();
        };
    }



    //因为实现了LOCK接口，以下是lock接口的一些实现，只需要将操作代理到自定义同步器Sync上即可
    private final Sync sync = new Sync();
    @Override
    public void lock() {
        //对于同步器，上锁就是将state置为1 ？？？
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

}

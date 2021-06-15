package com.shangcg.mythread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculator implements Calculator {

    private ForkJoinPool pool;

    private static class SumTask extends RecursiveTask<Long>{
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to){
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        // ForkJoin核心方法
        @Override
        protected Long compute() {

            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            } else {
                int middle = (from + to)/2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle + 1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }

    }

    public ForkJoinCalculator(){
//        pool = ForkJoinPool.commonPool();
        pool = new ForkJoinPool();
    }

    @Override
    public long sumUp(long[] numbers) {
        Long result = pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
        pool.shutdown();
        return result;
    }
}

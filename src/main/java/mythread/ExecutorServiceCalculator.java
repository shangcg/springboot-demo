package mythread;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

/**
 * Executor线程池 因启用线程池，该类会保留创建的子线程
 */
public class ExecutorServiceCalculator implements Calculator{

    private int cpuNumber;
    private ExecutorService pool;


    public ExecutorServiceCalculator(){
        cpuNumber = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(cpuNumber);
    }

    private static class SumTask implements Callable<Long>{
        private long[] numbers;
        private int from;
        private int to;
        private SumTask(long[] numbers, int from, int to){
            System.out.println(Thread.currentThread().getName());
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call(){
            System.out.println(Thread.currentThread().getName());
            long total = 0;
            for (int i = from; i <= to; i++){
                total += numbers[i];
            }
            return total;
        }
    }

    @Override
    public long sumUp(long[] numbers) {
        List<Future<Long>> results = new ArrayList<>();

        int part = numbers.length/cpuNumber;
        for (int i = 0; i < cpuNumber; i++){
            int from = i * part;
            int to = (i == cpuNumber -1) ? numbers.length - 1: (i +1) * part -1;

            results.add(pool.submit(new SumTask(numbers, from, to)));
        }

        long total = 0;
        for (Future<Long> f : results){
            try {
                total += f.get();
            }catch (Exception e){

            }
        }

        return total;
    }


    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ExecutorServiceCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();

        System.out.println("耗时：" + Duration.between(start, end).toMillis()+ "ms");

        System.out.println("结果为：" + result);
    }
}

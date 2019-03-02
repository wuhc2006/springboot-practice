package com.whc.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName ConcurrentCalculator
 * @Description TODO 多线程实现同步求和，ExecutoreService提供了submit()方法，
 * 传递一个Callable，或Runnable，返回Future。
 * 如果Executor后台线程池还没有完成Callable的计算，这调用返回Future对象的get()方法，会阻塞直到计算完成。
 * @Author Administrator
 * @Date 2018/12/16 20:54
 * @Version 1.0
 */
public class ConcurrentCalculator {
    private ExecutorService executorService;
    private int cpuCoreNumber;
    private List<Future<Long>> tasks = new ArrayList<>();

    public ConcurrentCalculator() {
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(cpuCoreNumber);
    }

    class SumCalculator implements Callable<Long> {
        private int[] numbers;
        private int start;
        private int end;


        public SumCalculator(final int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            Long sum = 0L;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        }
    }

    public Long sum(final int[] numbers) {
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
        for (int i = 0; i < cpuCoreNumber; i++) {
            int increment = numbers.length / cpuCoreNumber + 1;
            int start = increment * i;
            int end = increment * i + increment;
            if (end > numbers.length) {
                end = numbers.length;
            }
            SumCalculator subCalc = new SumCalculator(numbers, start, end);
            FutureTask<Long> task = new FutureTask<Long>(subCalc);
            tasks.add(task);
            if (!executorService.isShutdown()) {
                executorService.submit(task);
            }
        }
        return getResult();
    }

    /**
     * 迭代每个只任务，获得部分和，相加返回
     */
    public Long getResult() {
        Long result = 0L;
        for (Future<Long> task : tasks) {
            try {
                // 如果计算未完成则阻塞
                Long subSum = task.get();
                result += subSum;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close() {
        executorService.shutdown();
    }
}

package com.whc.cal.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Administrator
 * @date 2019/8/26 22:14
 */
public class ConcurrentTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try{
            List<Future<Integer>> tasks = new ArrayList<>();
            for (int i = 0; i < 10 ; i++){
                latch.countDown();
                Cal cal = new Cal(i*10 + 1, (i+1)*10);
                System.out.println(String.format("%s, %s", (i*10 + 1), (i+1)*10));
                tasks.add(executor.submit(cal));
            }
            latch.await();
            int sum = 0;
            for (int i = 0; i < 10; i++){
                sum += tasks.get(i).get();
                System.out.println("值：" + tasks.get(i).get());
            }
            System.out.println(sum);
        } finally {
            executor.shutdown();
        }
    }

    /**
     * 这是一个计算的接口
     */
    static class Cal implements Callable<Integer>{
        private final Integer min;
        private final Integer max;

        public Cal(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public Integer call() throws Exception {
            Integer sum = 0;
            for (int i= min; i <=max; i++){
                sum += i;
            }
            return sum;
        }
    }
}

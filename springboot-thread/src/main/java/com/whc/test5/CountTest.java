package com.whc.test5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CountTest
 * @Description TODO
 * “写十个线程,第一个线程求1到10的和,第二个11到20的和,
 * 第三个求21到30的和...第10个求91到100的和,求十个线程的和”。
 * 可以利用CyclicBarrier将分发后的任务汇总,将所有的线程去执行,执行结果后调用显示最后的结果线程。
 * @Author Administrator
 * @Date 2018/12/16 21:52
 * @Version 1.0
 */
public class CountTest {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10, new TotalTask(new MyCounter()));
        //CyclicBarrier指定了当10个线程运行结束时候,可以进行最后结果展示了
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i < 10;i++){
            int start = i*10+1;
            int end = start+9;
            CounterThread counterThread = new CounterThread(i, start, end, barrier);
            executorService.execute(counterThread);
        }
        executorService.shutdown();
    }
}

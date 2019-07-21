package com.whc.executor;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @ClassName ExecutorTest
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/7 15:46
 * @Version 1.0
 */
public class ExecutorTest implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + " is running...");
        int sum = 0;
        for (int i = 0; i < 10000000; i++) {
            sum += i;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = newFixedThreadPool(5);
        for (int i = 0; i < 500; i++) {
            executorService.execute(new ExecutorTest());
        }
        //executorService.shutdownNow();//立即关闭任务，抛弃任务
        executorService.shutdown();//将当前任务队列执行完后关闭
        System.out.println("complete shutdown.");
    }
}

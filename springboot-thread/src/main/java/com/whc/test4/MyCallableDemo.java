package com.whc.test4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName MyCallableDemo
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/16 21:33
 * @Version 1.0
 */
public class MyCallableDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("主程序开始运行了……");
        Date beginTime = new Date();

        //创建一个线程池
        int taskSize = 5;
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);

        //并发任务的返回值列表
        List<Future> futureList = new ArrayList<>();
        for(int i = 1;i <= taskSize; i++){
            //创建并发任务，执行并发任务并获取Future对象
            Future future = pool.submit(new MyCallable(i, i * 50000));
            futureList.add(future);
        }

        //close thread pool
        pool.shutdown();
        Thread.currentThread().sleep(1000);
        System.out.println("--------------");

        //获取所有并发执行任务的运行结果
        if (!futureList.isEmpty()){
            for (Future future : futureList){
                List<String> stringList = (List<String>) future.get();
                if (!stringList.isEmpty()){
                    System.out.println(">>>>耗时"+stringList.get(0)+"毫秒"+"求和结果："+stringList.get(1));

                }
            }
        }

        Date endTime = new Date();
        long time = endTime.getTime() - beginTime.getTime();
        System.out.println("\n---主程序执行结束，共运行时间【"+time+"】毫秒---");
    }
}

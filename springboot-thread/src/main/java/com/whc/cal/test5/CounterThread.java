package com.whc.cal.test5;

import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CounterThread
 * @Description TODO  单独的计算线程,比如计算{1...10}的相加
 * @Author Administrator
 * @Date 2018/12/16 21:52
 * @Version 1.0
 */
public class CounterThread implements Runnable {
    private int start;
    private int end;
    private CyclicBarrier barrier;

    public CounterThread(int id, int start, int end, CyclicBarrier barrier) {
        this.start = start;
        this.end = end;
        this.barrier = barrier;

        setName("Thread-"+id+"");
    }
    public void setName(String name){
        System.out.println(name);
    }

    @Override
    public void run() {
        int count = 0;
        for (int i = start; i<end+1;i++){
            count += i;
        }
        MyCounter.totalCount(count);

        try {
            barrier.wait();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}

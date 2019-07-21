package com.whc.fork;

import java.util.concurrent.*;

/**
 * 测试java8增强的ForkJoinPool
 * 对一个长度为size的数组元素进行累加
 *
 * @Author Administrator
 * @Date 2019/6/23 11:58
 */
public class CalculateTask extends RecursiveTask<Integer> {

    /**
     * 每个小任务至多累加20个数
     */
    private static final int THRESHOLD = 20;
    private static final long serialVersionUID = 924419308837637525L;
    private int arr[];
    private int start;
    private int end;

    public CalculateTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            // 递归分解任务
            CalculateTask left = new CalculateTask(arr, start, middle);
            CalculateTask right = new CalculateTask(arr, middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
            // 将两个小任务累加的和合并起来
            return left.join() + right.join();
        }
    }
}

class Sum {
    private static final int SIZE = 10000;
    public static void main(String[] args) throws Exception {
        int arr[] = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = i + 1;
        }
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // 提交可分解的CalculateTask任务
        ForkJoinTask<Integer> future = pool.submit(new CalculateTask(arr, 0, SIZE));
        System.out.println(future.get());
        pool.shutdown();
    }
}
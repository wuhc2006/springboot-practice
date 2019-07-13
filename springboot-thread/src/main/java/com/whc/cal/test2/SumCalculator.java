package com.whc.cal.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName SumCalculator
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/16 21:08
 * @Version 1.0
 */
public class SumCalculator {

    private class Sum implements Callable<Integer>{
        private int subMin;
        private int subMax;

        public Sum(int subMin, int subMax) {
            this.subMin = subMin;
            this.subMax = subMax;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = subMin; i <= subMax; i++){
                sum += 1;
                System.out.println(Thread.currentThread().getName()+"-----"+sum);
            }
            return sum;
        }
    }

    /**
     * 多线程求和min~max
     * @param min
     * @param max
     * @param threadSum
     * @return
     */
    public Integer getSum(int min, int max, int threadSum){
        int subMin;
        int subMax;
        List<FutureTask<Integer>> taskList = new ArrayList<>();
        int sumCounts = max - min + 1;
        int subCounts = sumCounts/threadSum;
        int remainder = subCounts%threadSum;
        int mark = min;
        for (int i = 0; i < threadSum;i++){
            subMin = mark;
            if (remainder!=0 && remainder >1){
                subMax = subMin + subCounts;
            }else{
                subMax = mark + subCounts - 1;
            }
            mark = subMax + 1;
            System.out.println(subMin+":"+subMax);
            FutureTask<Integer> task = new FutureTask<Integer>(new Sum(subMin,subMax));
            taskList.add(task);
            new Thread(task).start();
        }
        int sum = taskListSum(taskList);
        return sum;

    }

    private Integer taskListSum(List<FutureTask<Integer>> taskList) {
        int sum = 0;
        for (FutureTask<Integer> task: taskList){
            try {
                sum += task.get();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (ExecutionException e){
                e.printStackTrace();
            }
        }
        return sum;
    }


    public static void main(String[] args){
        SumCalculator sumCalculator = new SumCalculator();
        int sum = sumCalculator.getSum(1,100,5);
        System.out.println(sum);
    }
}

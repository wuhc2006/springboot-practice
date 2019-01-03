package com.whc.test3;

/**
 * @ClassName MultiThreadSum
 * @Description TODO 多线程执行加和操作
 * @Author Administrator
 * @Date 2018/12/16 21:23
 * @Version 1.0
 */
public class MultiThreadSum implements Runnable{
    private final MutableInteger sum;//和
    private final int fromInt;//起始的数
    private final int toInt;//终止的数
    private final int threaNumber;//线程个数

    public MultiThreadSum(MutableInteger sum, int fromInt, int toInt, int threaNumber) {
        this.sum = sum;
        this.fromInt = fromInt;
        this.toInt = toInt;
        this.threaNumber = threaNumber;
    }

    /**
     * 对sum进行加和计算，在sum原始值的基础上从 fromInt 开始加和，一直到 toInt 结束（包含fromInt 和 toInt）的数值
     */
    @Override
    public void run() {
        long current = System.currentTimeMillis();

        for (int i = fromInt; i <= toInt; i++) {
            this.sum.value += i;
        }

        current = System.currentTimeMillis() - current;
        System.out.println("Thread." + threaNumber + " executes sum from " + fromInt + " to " + toInt + " in " + current
                + " milseconds. Sum is " + sum.value);
    }


    public static void main(String[] args) {
        Integer toMax = 20000; //对从1到20,000进行加和
        Integer sumInteger = 0;
        int threads = 8; //计算线程数

        //每个线程计算一段连续的加和，并将加和结果保存在数组中。
        MutableInteger[] subSum = new MutableInteger[threads];
        for (int i = 0; i < threads; i++) {
            subSum[i] = new MutableInteger(0);
        }

        for (int i = 0; i < threads; i++) {
            int fromInt = toMax * i / threads + 1; //边界条件
            int toInt = toMax * (i + 1) / threads; //边界条件
            new Thread(new MultiThreadSum(subSum[i], fromInt, toInt, i)).start();
        }
        try {
            //等待加和程序执行结束,这个判断并不准确
            Thread.sleep(1000);

            for (int i = 0; i < threads; i++) {
                sumInteger += subSum[i].value;
            }
            System.out.println("The sum is :" + sumInteger);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

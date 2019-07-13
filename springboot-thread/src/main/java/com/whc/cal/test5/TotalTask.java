package com.whc.cal.test5;

/**
 * @ClassName TotalTask
 * @Description TODO 最后结算展示线程
 * @Author Administrator
 * @Date 2018/12/16 22:00
 * @Version 1.0
 */
public class TotalTask implements Runnable {

    private MyCounter myCounter;
    public TotalTask(MyCounter myCounter) {
        this.myCounter = myCounter;
    }

    @Override
    public void run() {
        System.out.println("所有线程运行完毕，总结果为；");
        int total = myCounter.totalResult();
        System.out.println(total);
    }
}

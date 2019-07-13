package com.whc.cal.test5;

/**
 * @ClassName MyCounter
 * @Description TODO 线程结果计算:将单独的线程的计算的结果相加,汇总的到总的结果
 * @Author Administrator
 * @Date 2018/12/16 21:57
 * @Version 1.0
 */
public class MyCounter {
    private static int count = 0;
    public synchronized static int totalCount(int perCount){
        count += perCount;
        return count;
    }

    public int totalResult(){
        return count;
    }
}

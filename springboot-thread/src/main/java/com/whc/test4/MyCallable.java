package com.whc.test4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @ClassName MyCallable
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/16 21:37
 * @Version 1.0
 */
public class MyCallable implements Callable<List<String>> {

    private int taskNumber;//任务编号
    private int number;//求和数量

    public MyCallable(int taskNumber, int number) {
        this.taskNumber = taskNumber;
        this.number = number;
    }

    @Override
    public List<String> call() throws Exception {
        System.out.println(">>>>>"+taskNumber + "任务启动");
        Date dateTmp1 = new Date();

        //执行具体的任务
        long sum = 0;
        for (int x = 1;x <= number; x++){
            sum += x;
        }

        //任务耗时
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>>>" + taskNumber +"任务结束");

        List<String> integerList = new ArrayList<>();
        integerList.add(String.valueOf(time));
        integerList.add(String.valueOf(sum));

        return integerList;
    }
}

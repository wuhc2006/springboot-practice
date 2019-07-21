package com.whc.concurrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示SimpleDataFormat的线程不安全用法：
 * SimpleDateFormat类中，有个对象calendar
 * 当SimpleDateFormat用static申明，多个线程共享SimpleDateFormat对象时，也共享该对象的calendar对象。
 * 而当调用parse方法时，会clear所有日历字段和值。当线程A正在调用parse，线程B调用clear，这样解析后的数据就会出现偏差
 *
 * @author Administrator
 * @date 2019/7/14 17:43
 */
public class SimpleDataFormatTest {
    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = "1111-11-11 11:11:11";
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        //多个线程操作同一个sdf对象
                        System.out.println(sdf.format(sdf.parse(dateStr)) + "---" + Thread.currentThread().getName());
                    } catch (ParseException e) {
                        System.out.println("--------------> error, " + e.getMessage());
                    }
                }
            });

        }
        executorService.shutdown();
    }
}

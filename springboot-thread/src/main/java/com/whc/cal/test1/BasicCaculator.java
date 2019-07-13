package com.whc.cal.test1;

/**
 * @ClassName BasicCaculator
 * @Description TODO  单线程求和
 * @Author Administrator
 * @Date 2018/12/16 21:04
 * @Version 1.0
 */
public class BasicCaculator {
    public static long sum(int[] numbers) {
        long sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

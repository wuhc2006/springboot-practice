package com.whc.sqrt;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 不用数学库，求出任何一个大于1的数的平方根
 *
 * @Author Administrator
 * @Date 2019/7/7 20:46
 */
public class SqrtTest {

    public static double sqrt(double value) {
        if (value < 1) {
            throw new IllegalArgumentException("参数不能小于1");
        } else if (value == 0) {
            return 0;
        } else {
            double low = 0;
            double high = value;
            double middle = (low + high) / 2;
            double tmp = middle * middle;
            int i = 0;
            while (high - low > 0.00000000001) {
                i++;
                if (tmp < value) {
                    low = middle;
                } else if (tmp > value) {
                    high = middle;
                } else {
                    return middle;
                }
                middle = (low + high) / 2;
                tmp = middle * middle;
            }
            System.out.println("遍历了" + i + "次找到答案!");
            return middle;
        }
    }

    public static void main(String[] args) {
        double value = 134635;
        System.out.println(value + "的平方根为:" + sqrt(value));
    }
}

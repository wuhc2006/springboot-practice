package com.whc.clazz;

import java.util.Random;

/**
 * 设置堆大小为20M，开启GC日志打印功能
 * -Xms20M -Xmx20M -XX:+PrintGCDetails
 */
public class GCLog {
    public static void main(String[] args) {
        Random rdRandom = new Random();
        while (true) {
            String s = String.valueOf(rdRandom.nextFloat());
        }
    }
}

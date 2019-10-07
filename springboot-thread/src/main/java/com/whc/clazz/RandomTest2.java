package com.whc.clazz;

import java.util.Arrays;
import java.util.Random;

/**
 * 以当前时间为种子生成器，每次都会生成不同的随机序列
 */
public class RandomTest2 {
    public static void main(String[] args) {
        Random rd = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(rd.nextFloat());
        }
        int[] a = {1,2};
        System.out.println(Arrays.toString(a));

        for (int j : a){
            System.out.println(j);
        }

        String[] array = {"a", "n", "s"};
        for (String it : array){
            System.out.println(it);
        }

        println("AA", "BB", "CC");
    }

    public static void println(Object...args){
        for (Object arg : args){
            System.out.println(arg);
        }
    }
}

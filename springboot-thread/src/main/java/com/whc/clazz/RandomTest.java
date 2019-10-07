package com.whc.clazz;

import java.util.Random;

/**
 * 相同种子生成器生成的随机数每次都是一样的
 * 0.72711575
 * 0.39982635
 * 0.5309454
 * 0.0534122
 * 0.16020656
 * 0.57799757
 * 0.18847865
 * 0.4170137
 * 0.51660204
 * 0.73734957
 */
public class RandomTest {
    public static void main(String[] args) {
        Random rd = new Random(47);
        for (int i = 0; i< 10; i++){
            System.out.println(rd.nextFloat());
        }
    }
}

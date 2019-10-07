package com.whc.clazz;

/**
 * 测试类的初始
 */
public class TestInitialization {
    static {
        i = 0;
        //System.out.println(i);// 会出现非法的向前引用
    }

    static int i = 1;
}

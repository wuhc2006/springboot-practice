package com.whc.clazz;

/**
 * 通过子类引用父类的静态字段，不会导致子类初始化
 */
public class SuperClass {
    static {
        System.out.println("Super Class initialized.");
    }

    public static int value = 123;
}

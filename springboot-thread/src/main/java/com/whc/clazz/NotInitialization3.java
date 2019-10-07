package com.whc.clazz;

/**
 * 非主动使用类的字段
 */
public class NotInitialization3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLO_WORLD);
    }
}

package com.whc.clazz;

/**
 * 通过数组定义来引用类，不会出发此类的初始化
 */
public class NotInitialization2 {
    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }
}

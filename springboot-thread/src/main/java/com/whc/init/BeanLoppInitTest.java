package com.whc.init;

/**
 * 测试循环依赖问题
 */
public class BeanLoppInitTest {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);

        System.out.println(a);
        System.out.println(b);
    }
}

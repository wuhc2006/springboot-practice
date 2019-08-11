package com.whc.inter;

/**
 * 测试接口的默认实现
 * @author Administrator
 * @date 2019/8/11 13:01
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new GreetImpl().hello());
        System.out.println(new GreetImpl().world());
        System.out.println(new GreetImpl().smile());
        Thread.interrupted();
    }
}

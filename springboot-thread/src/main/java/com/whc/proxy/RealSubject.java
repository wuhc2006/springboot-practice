package com.whc.proxy;

/**
 * @ClassName RealSubject
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/16 22:59
 * @Version 1.0
 */
public class RealSubject implements Subject {
    @Override
    public void sell(String name) {
        System.out.println("book name is:" + name);
    }

    @Override
    public void buy() {
        System.out.println("I've buy a book!");
    }
}

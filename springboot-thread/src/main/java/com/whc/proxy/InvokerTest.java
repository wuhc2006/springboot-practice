package com.whc.proxy;

import java.lang.reflect.Proxy;

/**
 * @ClassName InvokerTest
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/16 23:03
 * @Version 1.0
 */
public class InvokerTest {

    public static void main(String[] args) {
        Subject subject = new RealSubject();
        MyInvocationHandler handler = new MyInvocationHandler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), new Class[]{Subject.class}, handler);

        proxy.sell("莎士比亚");
        proxy.buy();
    }
}

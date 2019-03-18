package com.whc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MyInvocationHandler
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/16 23:01
 * @Version 1.0
 */
public class MyInvocationHandler implements InvocationHandler {

    Subject realSubject;

    public MyInvocationHandler(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行方法前......");
        method.invoke(realSubject, args);
        System.out.println("执行方法后......");
        return null;
    }
}

package com.whc.clazz;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JAVA方法区OOM，方法区存储：类名、访问修饰符、常量池、字段描述和方法描述等
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, arg);
                }
            });
        }
    }

    private static class OOMObject{}
}

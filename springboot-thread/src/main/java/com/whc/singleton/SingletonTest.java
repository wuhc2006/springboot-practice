package com.whc.singleton;

/**
 * 通过枚举实现单例模式
 */
public class SingletonTest {

    private SingletonTest() {

    }

    public static SingletonTest getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonTest singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonTest();
        }

        public SingletonTest getInstance() {
            return singleton;
        }
    }
}

package com.whc.singleton;

/**
 * 单例模式的双检锁
 */
public class SingletonDoubleLock {
    private volatile static SingletonDoubleLock instance;

    private SingletonDoubleLock() {
    }

    public static SingletonDoubleLock getInstance(){
        if (null == instance){
            synchronized (SingletonDoubleLock.class){
                if (null == instance){
                    return new SingletonDoubleLock();
                }
            }
        }
        return instance;
    }
}

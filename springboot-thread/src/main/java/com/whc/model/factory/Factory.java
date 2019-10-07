package com.whc.model.factory;

/**
 * 抽象工厂类
 */
public interface Factory {
    String getName();
    void createUser();
    void createDept();
}

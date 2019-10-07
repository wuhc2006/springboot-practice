package com.whc.model.factory;

/**
 * 抽象工厂
 */
public abstract class AbstractFactory implements Factory{
    public String getName(){
        return "abstract";
    }
}

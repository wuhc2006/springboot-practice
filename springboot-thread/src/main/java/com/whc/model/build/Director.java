package com.whc.model.build;

public class Director {
    public void construct(Builder builder){
        builder.buildPartA();
        builder.buildPartB();
        builder.getResult();
    }
}

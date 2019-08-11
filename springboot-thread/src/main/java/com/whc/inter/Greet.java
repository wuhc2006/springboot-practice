package com.whc.inter;

public interface Greet {
    default String hello(){return "hello";}
    default String smile(){return "Li smile";}
}

package com.whc.inter;

public interface Greet2 {
    default String world(){return "world";}
    default String smile(){return "Han smile";}
}

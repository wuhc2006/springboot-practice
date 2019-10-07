package com.whc.model.build2;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示建造者模式，产品类
 */
public class Product {
    List<String> parts = new ArrayList<>();
    public void add(String part){
        parts.add(part);
    }

    @Override
    public String toString() {
        return String.join(",", parts);
    }
}

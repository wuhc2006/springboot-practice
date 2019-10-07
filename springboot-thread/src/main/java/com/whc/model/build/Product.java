package com.whc.model.build;

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

    public void show(){
        System.out.println("产品创建...");
        System.out.println(String.join(",", parts));
    }
}

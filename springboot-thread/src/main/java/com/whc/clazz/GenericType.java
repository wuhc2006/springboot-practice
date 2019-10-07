package com.whc.clazz;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除演示
 */
public class GenericType {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);

        List<String> list2 = new ArrayList<>();
        list2.add("String 2");

        System.out.println(list1.getClass().equals(list2.getClass()));
    }
}

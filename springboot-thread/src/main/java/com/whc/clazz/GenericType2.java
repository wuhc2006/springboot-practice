package com.whc.clazz;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除演示2：通过反射写入一个不符合泛型类型的list中
 */
public class GenericType2 {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.getClass().getMethod("add", Object.class).invoke(list, "ABC");
        for (Object l : list){
            System.out.println(l);
        }
    }
}

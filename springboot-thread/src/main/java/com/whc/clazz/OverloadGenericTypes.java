package com.whc.clazz;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class OverloadGenericTypes {
   /* public static void method(List<String> list){
        System.out.println("invoke String list");
    }

    public static void method(List<Integer> list){
        System.out.println("invoke Integer list");
    }*/
   public static void main(String[] args) {
       List<Integer> list = Arrays.asList(1, 2,3);
   }
}

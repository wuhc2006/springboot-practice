package com.whc.tools;

/**
 * @author Administrator
 * @date 2019/7/8
 */
public class StringJoinTest {
    public static void main(String[] args) {
        String name = ",save,delete";
        String[] events = name.split(",");
        System.out.println(String.join(",", events));
    }
}

package com.whc.ref;

/**
 * 字符串比较
 *
 * @author Administrator
 * @date 2019/4/10
 */
public class StringRefEqual {
    public static void main(String[] args) {
        String a = "admin";
        String b = "ad" + "min";
        String c = new StringBuffer().append("ad").append("min").toString();
        String d = new String("admin");
        System.out.println(a == b);// True,都是从常量池取数，相同对象
        System.out.println(a == c);// false,不是一个对象
        System.out.println(a == d);// false
        System.out.println(c == d);// false
    }
}

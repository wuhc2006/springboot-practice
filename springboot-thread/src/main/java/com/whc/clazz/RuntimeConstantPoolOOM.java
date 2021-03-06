package com.whc.clazz;

public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);// true

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);// false

        String str3 = "ja" + "va";
        System.out.println(str3.intern() == str3);// true
    }
}

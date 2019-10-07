package com.whc.clazz;

import java.io.Serializable;

/**
 * 重载方法优先级
 */
public class Overload {
    public static void sayHello(Object args){
        System.out.println("hello Object!");
    }
    public static void sayHello(int arg){
        System.out.println("hello int");
    }

    public static void sayHello(long arg){
        System.out.println("hello long");
    }

    public static void sayHello(Character arg){
        System.out.println("hello character");
    }

//    public static void sayHello(char arg){
//        System.out.println("hello char");
//    }

    public static void sayHello(char... arg){
        System.out.println("hello char...");
    }
    public static void sayHello(Serializable arg){
        System.out.println("hello serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
        System.getProperties().list(System.out);
    }
}

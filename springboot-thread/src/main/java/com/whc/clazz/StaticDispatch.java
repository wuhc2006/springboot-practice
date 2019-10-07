package com.whc.clazz;

/**
 * 方法静态分派
 * 输出结果：
 * hello, guy!
 * hello, guy!
 * 注意：编译器在重载的时候是根据参数的静态类型而不是实际类型作为判断依据的，两次调用的都是sayHello(Human)方法
 */
public class StaticDispatch {
    static abstract class Human{}

    static class Man extends Human{
    }

    static class Woman extends Human{
    }

    public void sayHello(Human guy){
        System.out.println("hello, guy!");
    }

    public void sayHello(Man guy){
        System.out.println("hello gentleman!");
    }

    public void sayHello(){
        System.out.println("hello, lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }
}

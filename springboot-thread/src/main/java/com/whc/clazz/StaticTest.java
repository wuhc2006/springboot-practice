package com.whc.clazz;

/**
 * 输出结果： 1 2 3 a=110,b=0 5 4
 * 加载的顺序：
 * 1. 按顺序执行static方法及语句；
 * 2. 如果static遇到new，则先执行代码块或成员变量语句，之后调用构造方法，执行成员函数等
 */
public class StaticTest {
    public static void main(String[] args) {
        staticFunction();
    }

    static
    {
        System.out.println("1");
    }

    int a=110;
    {
        System.out.println("2");
    }

    private static StaticTest test = new StaticTest();
    static {
        System.out.println(test.get());
    }

    StaticTest()
    {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    String get(){
        return "5";
    }

    private static void staticFunction() {
        System.out.printf("4");
    }

    static int b =112;
}

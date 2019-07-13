package com.whc.abstra;

/**
 * 接口是最抽象的一层，只定义需要实现的接口函数及功能
 *
 * @author Administrator
 * @date 2019/4/11
 */
public interface Context {
    // 接口中定义的成员变量默认为static
    String name = "admin";
    int age = 18;

    Object getName();
    Object getAge();
    void set();
}

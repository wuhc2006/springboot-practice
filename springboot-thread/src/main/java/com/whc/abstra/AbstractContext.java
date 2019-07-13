package com.whc.abstra;

import java.util.Date;

/**
 * 抽象类实现接口，可以有抽象方法也可以具体实现
 *
 * @author Administrator
 * @date 2019/4/11
 */
public abstract class AbstractContext implements Context, Invocation {

    /**
     * 覆盖接口类的属性
     */
    public String name = "admin1";

    /**
     * 抽象类可以写新定义的方法
     *
     * @return
     */
    protected String getAccount() {
        return "100";
    }

    /**
     * 可以在抽象类实现接口的方法
     *
     * @return
     */
    @Override
    public Object getName() {
        return "admin";
    }

    /**
     * 抽象类中可以定义类似接口函数，但必须加上abstract字段
     *
     * @return
     */
    abstract Object getEmail();

    /**
     * 抽象类实现接口的方法
     *
     * @return
     */
    @Override
    public Object getCreateTime() {
        return new Date();
    }

    /**
     * 抽象类实现接口的方法
     *
     * @return
     */
    @Override
    public Object getCreator() {
        return "administrator";
    }
}

package com.whc.inter;

/**
 * 多实现，必须实现接口相同方法
 * @author Administrator
 * @date 2019/8/11 13:00
 */
public class GreetImpl implements Greet, Greet2 {
    @Override
    public String smile() {
        return "who is smile?";
    }
}

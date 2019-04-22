package com.whc.singleton;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @ClassName SingletonUser
 * @Description 单例的user
 * @Author Administrator
 * @Date 2019/3/27 20:45
 * @Version 1.0
 */
@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)// 默认为单例，这里可以不写
public class SingletonUser {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.whc.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/27 20:42
 * @Version 1.0
 */
@Service
public class UserService1 {

    @Autowired
    private PrototypeUser prototypeUser;
    @Autowired
    private SingletonUser singletonUser;

    public void testProto(){
        prototypeUser.setName("李四");
        System.out.println(prototypeUser.getName());
    }

    public void testSingleton(){
        singletonUser.setName("X");
        System.out.println(singletonUser.getName());
    }

    public PrototypeUser getPrototypeUser() {
        return prototypeUser;
    }

    public void setPrototypeUser(PrototypeUser prototypeUser) {
        this.prototypeUser = prototypeUser;
    }

    public SingletonUser getSingletonUser() {
        return singletonUser;
    }

    public void setSingletonUser(SingletonUser singletonUser) {
        this.singletonUser = singletonUser;
    }
}

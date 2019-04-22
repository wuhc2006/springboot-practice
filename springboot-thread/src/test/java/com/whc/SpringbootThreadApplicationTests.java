package com.whc;

import com.whc.singleton.UserService1;
import com.whc.singleton.UserService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootThreadApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserService1 userService1;
    @Autowired
    private UserService2 userService2;

    /**
     * 测试单例
     */
    @Test
    public void testUser(){
        userService1.testProto();
        userService2.testProto();

        userService1.testSingleton();
        userService2.testSingleton();

        /**
         * 为false，说明是不同对象，多例
         */
        System.out.println(userService1.getPrototypeUser().equals(userService2.getPrototypeUser()));
        /**
         * 为true，说明是同一个对象，单例
         */
        System.out.println(userService1.getSingletonUser().equals(userService2.getSingletonUser()));
    }




}


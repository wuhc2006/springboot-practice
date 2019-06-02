package com.whc;

import com.whc.controller.FirstSender;
import com.whc.controller.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }

    @Autowired
    private FirstSender firstSender;

    @Test
    public void send() throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i < 100; ) {
            firstSender.send(uuid, "hello world, this id send by the " +(++i)+ " one...");
            System.out.println("I am sending you " + i + " message.");
            Thread.sleep(100);
        }

    }
}


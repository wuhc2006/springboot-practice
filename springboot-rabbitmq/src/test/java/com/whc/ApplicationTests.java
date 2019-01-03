package com.whc;

import com.whc.controller.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }
}


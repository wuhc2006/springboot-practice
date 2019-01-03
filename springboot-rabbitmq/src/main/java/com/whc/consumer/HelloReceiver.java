package com.whc.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloReceiver
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/8 19:48
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "whc")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("Receiver  : " + hello);
    }
}

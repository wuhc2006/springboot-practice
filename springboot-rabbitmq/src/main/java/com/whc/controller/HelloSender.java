package com.whc.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName HelloSender
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/8 19:47
 * @Version 1.0
 */
@RestController
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 发送单条信息
     */
    @GetMapping("/send")
    public void send() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String context = "hello " + sdf.format(new Date());
        System.out.println("Sender : " + context);
        //通过rabbitTemplate.convertAndSend发送context到whc队列中
        this.rabbitTemplate.convertAndSend("whc", context);
    }

    /**
     * 循环发送多条信息
     */
    @GetMapping("/multiSend")
    public void multiSend() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 1000; i++) {
            String context = "你好，这是我的第 " + i + "条消息，当前时间为：" + sdf.format(new Date());
            System.out.println("Sender : " + context);
            this.rabbitTemplate.convertAndSend("whc", context);
        }
    }
}

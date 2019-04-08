package com.whc.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 这里有两个消费者FirstConsumer和HelloReceiver,都订阅了whc这个消息队列，消息消费的原则是：只能由一个消费者消费
 *
 * @author Administrator
 * @date 2019/4/8
 */
@Component
public class FirstConsumer {
    @RabbitListener(queues = {"whc"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(String message) throws Exception {
        System.out.println("FirstConsumer {} handle message..." + message);
    }
}

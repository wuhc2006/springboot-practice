package com.whc.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 消息发送到交换机确认机制
 * @author Administrator
 * @date 2019/4/8
 */
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("MsgSendConfirmCallBack, 回调id: " + correlationData);
        if (ack){
            System.out.println("消息消费成功！");
        }else{
            System.out.println("消息消费失败，原因: " + cause + "，\n 重新发送。");
        }
    }
}

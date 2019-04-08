package com.whc.config;

import com.whc.callback.MsgSendConfirmCallBack;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 */
@Configuration
public class RabbitMqConfig {
    /**
     * 消息交换机的名字
     */
    public static final String EXCHANGE = "exchange_test";
    /**
     * 队列key1
     */
    public static final String ROUTE_KEY_1 = "query_one_key1";
    /**
     * 队列key2
     */
    public static final String ROUTE_KEY_2 = "query_one_key2";

    @Autowired
    private QueueConfig queueConfig;
    @Autowired
    private ExchangeConfig exchangeConfig;

    /**
     * 连接工厂
     */
    @Autowired
    private ConnectionFactory factory;

    /**
     * 将消息队列和交换机通过路由key绑定
     *
     * @return
     */
    @Bean
    public Binding bindingOne() {
        return BindingBuilder.bind(queueConfig.queue()).to(exchangeConfig.directExchange()).with(RabbitMqConfig.ROUTE_KEY_1);
    }

    /**
     * queue监听器，观察者模式
     * 当有消息到达时会通知监听在对应队列上的对象
     *
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.addQueues(queueConfig.queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(5);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }

    /**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务
     *
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setConfirmCallback(msgSendConfirmCallBack());
        return rabbitTemplate;
    }

    @Bean
    public MsgSendConfirmCallBack msgSendConfirmCallBack() {
        return new MsgSendConfirmCallBack();
    }
}

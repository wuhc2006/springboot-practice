package com.whc.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息交换机配置  可以配置多个
 *
 * @Author administrator
 * @date 2019/4/5
 */
@Configuration
public class ExchangeConfig {

    /**
     * direct exchange: routing key完全匹配才转发
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMqConfig.EXCHANGE, true, false);
    }

    /**
     * fanout exchange: 不理会routing key,消息直接广播到所有绑定的queue
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMqConfig.EXCHANGE, true, false);
    }

    /**
     * topic exchange : 对routing key模式匹配
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitMqConfig.EXCHANGE, true, false);
    }
}

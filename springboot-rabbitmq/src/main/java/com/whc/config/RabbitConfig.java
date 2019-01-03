package com.whc.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitConfig
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/8 19:46
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue queue(){
        return new Queue("whc");
    }
}

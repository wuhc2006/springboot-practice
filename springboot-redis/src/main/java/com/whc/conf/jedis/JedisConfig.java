package com.whc.conf.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisConfig
 * @Description TODO 注册jedispool
 * @Author Administrator
 * @Date 2019/3/10 11:02
 * @Version 1.0
 */
@Configuration
@EnableCaching
@PropertySource("classpath:redis.properties")
public class JedisConfig extends CachingConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);

    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;

    @Value("${password}")
    private String password;

    @Value("${database}")
    private int database;

    @Value("${timeout}")
    private int timeout;

    @Bean(name = "jedisPool")
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, StringUtils.isEmpty(password)?null:password, database);
        logger.info("JedisPool注入成功！");
        logger.info("redis地址：" + host + ":" + port);
        return jedisPool;
    }

}

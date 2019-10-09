package com.whc.conf.jedis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Administrator
 * @date 2019/5/7
 */
//@Configuration
public class DruidConfig {
    /*@ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidSource(){
        return new DruidDataSource();
    }*/
}

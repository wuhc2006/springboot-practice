package com.whc.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * es client的配置
 * @author Administrator
 * @date 2019/1/12 17:54
 */
@Configuration
public class MyConfig {

    @Bean
    public TransportClient client() throws UnknownHostException {
        // 注意：这个端口默认为9300
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("localhost"), 9300);

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings);

        client.addTransportAddress(transportAddress);
        return client;
    }
}

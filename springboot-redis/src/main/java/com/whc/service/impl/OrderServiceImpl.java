package com.whc.service.impl;

import com.whc.conf.jedis.RedisCache;
import com.whc.domain.Order;
import com.whc.mq.Consumer;
import com.whc.mq.Producer;
import com.whc.service.OrderService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/10 16:00
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static AtomicInteger userId = new AtomicInteger(0);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static final int MAX_THREADS = 8;
    private volatile static List<Order> list = new CopyOnWriteArrayList();

    @Override
    public void push() {
        BigDecimal price = new BigDecimal("100.00");
        Random random = new Random();
        String goodsId = "1";
        // 模拟1000个订单
        for (int i = 0; i < 1000; i++) {
            int count = random.nextInt(10);
            BigDecimal totalPrice = price.multiply(new BigDecimal(count));
            userId.getAndIncrement();
            Order order = new Order(userId.get() + "", goodsId, count, totalPrice, new Date());
            list.add(order);

            Jedis jedis = RedisCache.getJedis();
            jedis.lpush(order.getGoodsId() + "", order.toString());
            System.out.println("提交订单成功！订单信息为：" + order.toString());
            jedis.close();
        }

    }

    @Override
    public void pop() {
        for (int i = 0; i < MAX_THREADS; i++) {
            new Thread(new Consumer("1"), i + "").start();
        }
    }
}

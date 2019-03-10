package com.whc;

import com.alibaba.fastjson.JSON;
import com.whc.domain.Order;
import com.whc.mq.Consumer;
import com.whc.mq.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void contextLoads() {
    }

    /**
     * 插入字符串
     */
    @Test
    public void setString() {
        redisTemplate.opsForValue().set("springboot-redis", "springboot redis string set!");
    }

    /**
     * 获取字符串
     *
     * @return
     */
    @Test
    public void getString() {
        System.err.println(redisTemplate.opsForValue().get("springboot-redis"));
    }

    @Test
    public void setObj() {
        Person person = new Person("admin", 23, "ww@163.com", new BigDecimal(1000.00));
        redisTemplate.opsForValue().set("redis_obj_key", JSON.toJSONString(person));
    }

    @Test
    public void getObj() {
        System.err.println(redisTemplate.opsForValue().get("redis_obj_key").toString());
    }

    @Test
    public void mq() {
        String goodsId = "1";
        BigDecimal price = new BigDecimal("100.00");
        Random random = new Random();
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 模拟1000个人往线程池提交了任务
        for (int i = 0; i < 1000; i++) {
            int count = random.nextInt(10);
            BigDecimal totalPrice = price.multiply(new BigDecimal(count));
            Order order = new Order(i+"", goodsId, count, totalPrice, new Date());
            Producer producer = new Producer(order);
            executorService.execute(producer);
        }
        // 消费
        Consumer consumer = new Consumer(goodsId);
        executorService.execute(consumer);
    }

}


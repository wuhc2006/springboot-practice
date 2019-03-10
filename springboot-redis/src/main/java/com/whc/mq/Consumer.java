package com.whc.mq;

import com.whc.conf.jedis.RedisCache;
import com.whc.domain.Order;
import com.whc.util.SerializeUtil;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @ClassName Consumer
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/10 15:33
 * @Version 1.0
 */
public class Consumer implements Runnable {

    private String goodsId;

    public Consumer(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public void run() {
        Jedis jedis = RedisCache.getJedis();
        long startTime = System.currentTimeMillis();
        while (jedis.llen(goodsId) > 0) {
            String order = jedis.lpop(goodsId);
            try {
                System.out.println("当前线程:" + Thread.currentThread().getName() + ",正在处理订单" + order);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        long endTime = System.currentTimeMillis();
        long cost = endTime - startTime;
        System.out.println("订单处理完毕，耗时：" + cost);
        jedis.close();
    }
}

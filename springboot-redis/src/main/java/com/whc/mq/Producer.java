package com.whc.mq;

import com.whc.conf.jedis.RedisCache;
import com.whc.domain.Order;
import redis.clients.jedis.Jedis;

/**
 * 生产某种商品的订单
 *
 * @author Administrator
 * @date 2019/3/10 15:33
 */
public class Producer implements Runnable {

    private Order order;

    public Producer(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        Jedis jedis = RedisCache.getJedis();
        jedis.lpush(order.getGoodsId()+"", order.toString());
        System.out.println("提交订单成功！订单信息为：" + order.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jedis.close();
    }
}

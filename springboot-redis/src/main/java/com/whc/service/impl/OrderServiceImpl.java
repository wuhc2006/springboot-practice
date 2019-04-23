package com.whc.service.impl;

import com.whc.conf.jedis.RedisCache;
import com.whc.dao.GoodsDao;
import com.whc.dao.OrderDao;
import com.whc.domain.Goods;
import com.whc.domain.Order;
import com.whc.domain.SeckillStatus;
import com.whc.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
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

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
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
        Jedis jedis = RedisCache.getJedis();
        for (int i = 0; i < 1000; i++) {
            int count = random.nextInt(10);
            BigDecimal totalPrice = price.multiply(new BigDecimal(count));
            userId.getAndIncrement();
            Order order = new Order(userId + "", goodsId, count, totalPrice, new Date());
            list.add(order);

            jedis.lpush(order.getGoodsId() + "", order.toString());
            System.out.println("提交订单成功！订单信息为：" + order.toString());
        }
        jedis.close();
    }

    @Override
    public void pop() {
        String goodsId = "1";
        final CountDownLatch latch = new CountDownLatch(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < MAX_THREADS; i++) {
            new Thread(() -> {
                handleOrder(goodsId);
            }).start();
            latch.countDown();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*long endTime = System.currentTimeMillis();
        long cost = endTime - startTime;
        System.out.println("订单处理完成，耗时：" + cost + "ms");*/
    }

    /**
     * 处理订单
     *
     * @param goodsId
     */
    private void handleOrder(String goodsId) {
        Jedis jedis = RedisCache.getJedis();
        while (jedis.llen(goodsId) > 0) {
            String order = jedis.lpop(goodsId);
            try {
                logger.info("当前线程:" + Thread.currentThread().getName() + ",正在处理订单" + order);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        jedis.close();
    }


    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private OrderDao orderDao;

    @Transactional
    @Override
    public SeckillStatus buy(String goodsId, String userId) {
        // 1. 查库存
        if (query(goodsId) < 0) {
            return SeckillStatus.SOLD_OUT;
        }
        if (hasOrder(goodsId, userId)){
            return SeckillStatus.REPEAT_SEC;
        }
        Goods goods = goodsDao.findById(Long.parseLong(goodsId)).get();
        // 2. 库存扣减
        goods.setInventory(goods.getInventory() - 1);
        // 3. 生成订单
        Order order = new Order();
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setCount(1);
        order.setCreateTime(new Date());
        order.setTotalPrice(goods.getSellPrice());

        goodsDao.save(goods);
        orderDao.save(order);
        // 3. 更新库存缓存
        RedisCache.putObject(goodsId, goods.getInventory(), 60);
        return SeckillStatus.SUCESS;
    }


    /**
     * 查库存
     * @param goodsId 商品ID
     * @return
     */
    @Override
    public int query(String goodsId) {
        Object object = RedisCache.getObject(goodsId);
        if (object == null) {
            Goods goods = goodsDao.findById(Long.parseLong(goodsId)).get();
            RedisCache.putObject(goodsId, goods.getInventory(), 60);
            return goods.getInventory();
        }
        return Integer.valueOf(object.toString());
    }

    /**
     * 判断是否有过该订单
     *
     * @param goodsId 商品ID
     * @param userId  用户ID
     * @return 是否购买过
     */
    public boolean hasOrder(String goodsId, String userId) {
        List<Order> orders = orderDao.findOrderGoodsIdAndUserId(goodsId, userId);
        return !(orders == null || orders.isEmpty());
    }

}

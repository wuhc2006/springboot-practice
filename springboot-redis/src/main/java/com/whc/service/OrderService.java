package com.whc.service;

import com.whc.domain.Order;
import com.whc.domain.SeckillStatus;

/**
 * @ClassName OrderService
 * @Description TODO 处理订单信息
 * @Author Administrator
 * @Date 2019/3/10 15:27
 * @Version 1.0
 */
public interface OrderService {
    /**
     * 将订单详情push到消息队列
     */
    void push();

    /**
     * 处理某种商品的订单
     */
    void pop();

    /**
     * 用户购买接口
     */
    SeckillStatus buy(String goodsId, String userId);

    /**
     * 查询商品库存数量
     *
     * @param goodsId 商品ID
     * @return 库存数量
     */
    int query(String goodsId);
}

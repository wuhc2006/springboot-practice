package com.whc.service;

import com.whc.domain.Order;

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
     *处理某种商品的订单
     */
    void pop();

    /**
     * 用户购买接口
     */
    void buy();
}

package com.whc.dao;

import com.whc.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/4/23
 */
public interface OrderDao extends CrudRepository<Order, Long> {
    List<Order> findOrderGoodsIdAndUserId(String goodsId, String orderId);
}

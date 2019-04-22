package com.whc.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO 订单信息
 * @Author Administrator
 * @Date 2019/3/10 15:38
 * @Version 1.0
 */
@Data
public class Order {

    /**
     * 订单id
     */
    private Long id;

    /**
     *购买的用户
     */
    private String userId;
    /**
     * 商品
     */
    private String goodsId;
    /**
     * 购买的数量
     */
    private Integer count;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 订单创建时间
     */
    private Date createTime;

    public Order(String id, String goodsId, int count, BigDecimal totalPrice, Date createTime) {
        this.id = Long.parseLong(id);
        this.goodsId = goodsId;
        this.count = count;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
    }
}

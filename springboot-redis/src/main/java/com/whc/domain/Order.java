package com.whc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单信息
 *
 * @author Administrator
 * @date 2019/3/10 15:38
 */
@Entity
@Table(name = "t_order")
@Data
public class Order {

    /**
     * 订单id
     */
    @Id
    @GeneratedValue
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

    public Order(String userId, String goodsId, Integer count, BigDecimal totalPrice, Date createTime) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.count = count;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
    }

    public Order() {
    }
}

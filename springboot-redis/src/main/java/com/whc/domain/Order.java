package com.whc.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO 订单信息
 * @Author Administrator
 * @Date 2019/3/10 15:38
 * @Version 1.0
 */
public class Order {

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

    @Override
    public String toString() {
        return "Order{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", createTime=" + createTime +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

package com.whc.domain;

import java.math.BigDecimal;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/10 15:39
 * @Version 1.0
 */
public class Goods {

    /**
     * 商品id
     */
    private String id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 售价
     */
    private BigDecimal sellPrice;
    /**
     * 总数量
     */
    private Integer amount;
    /**
     * 库存
     */
    private Integer inventory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}

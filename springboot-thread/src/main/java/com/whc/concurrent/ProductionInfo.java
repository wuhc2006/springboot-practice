package com.whc.concurrent;

import java.math.BigDecimal;

/**
 * @author Administrator
 * @date 2019/7/20 20:50
 */
public class ProductionInfo {
    private String name;
    private BigDecimal price;

    public ProductionInfo(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductionInfo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

package com.whc.model.consumer;

import java.math.BigDecimal;

/**
 * 货物，用于生产者和消费者模式
 */
public class Goods {
    private int id;
    private String name;
    private BigDecimal price;

    public Goods(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

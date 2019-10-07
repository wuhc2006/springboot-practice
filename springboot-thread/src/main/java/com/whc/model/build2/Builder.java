package com.whc.model.build2;

/**
 * 简化版的建造者模式
 */
public class Builder {

    private Product product;

    /**
     * 默认设置
     */
    public Builder() {
        product = new Product();
        product.add("test");
    }

    /**
     * 建造A
     * @return
     */
    public Builder addA(){
        product.add("part A");
        return this;
    }

    /**
     * 建造B
     * @return
     */
    public Builder addB(){
        product.add("part B");
        return this;
    }

    /**
     * 建造完成
     * @return
     */
    public Product build(){
        return this.product;
    }
}

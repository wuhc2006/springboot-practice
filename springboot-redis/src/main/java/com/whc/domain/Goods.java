package com.whc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * @date 2019/3/10 15:39
 */
@Entity
@Table(name = "t_goods")
@Data
public class Goods {

    /**
     * 商品id
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 商品名称
     */
    private String goods_name;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 售价
     */
    private BigDecimal sellPrice;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 总数量
     */
    private Integer amount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;



}

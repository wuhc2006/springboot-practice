package com.whc.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/12 13:20
 * @Version 1.0
 */

/**
 * indexName必须为小写，可理解为数据库名
 */
@Document(indexName = "goodsinfo", type = "goods")
public class Goods implements Serializable {
    private Long id;
    private String name;
    private String description;

    /**
     * 必须添加无参构造器，但是jackson的反序列化需要使用无参构造函数
     */
    public Goods() {
    }

    public Goods(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

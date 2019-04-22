package com.whc.dao;

import com.whc.domain.Goods;
import org.springframework.data.repository.CrudRepository;

/**
 * @ClassName GoodsDao
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/22 22:36
 * @Version 1.0
 */
public interface GoodsDao extends CrudRepository<Goods, Long> {
}

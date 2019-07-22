package com.whc.dao;

import com.whc.domain.Goods;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Administrator
 * @date 2019/4/22 22:36
 */
public interface GoodsDao extends CrudRepository<Goods, Long> {
}

package com.whc.repository;

import com.whc.entity.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2019/1/12 13:24
 */
@Component
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}

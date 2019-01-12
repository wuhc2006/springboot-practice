package com.whc.repository;

import com.whc.entity.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @ClassName GoodsRepository
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/12 13:24
 * @Version 1.0
 */
@Component
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}

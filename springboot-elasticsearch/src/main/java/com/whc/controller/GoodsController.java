package com.whc.controller;

import com.whc.entity.Goods;
import com.whc.repository.GoodsRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName GoodsController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/12 13:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * 保存
     * @param name
     * @param description
     * @return
     */
    @RequestMapping("/save")
    public String save(String name, String description){
        Goods goods = new Goods(System.currentTimeMillis(), name, description);
        goodsRepository.save(goods);
        return "success";
    }

    /**
     * 更新
     * @param id
     * @param name
     * @param description
     * @return
     */
    @RequestMapping("/update")
    public String update(Long id, String name, String description){
        Goods goods = new Goods(id, name, description);
        goodsRepository.save(goods);
        return "success";
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @RequestMapping("/selectOne")
    public Optional<Goods> selectOne(Long id){
        return goodsRepository.findById(id);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(Long id){
        goodsRepository.deleteById(id);
        return "success";
    }

    /**
     * 限定关键词的搜索
     * @param name
     * @param pageable
     * @return
     */
    @RequestMapping("/getGoodsListByItem")
    public List<Goods> getGoodsListByItem(String name, @PageableDefault(page=0, value = 10) Pageable pageable){
        //设置分页
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);
        Page<Goods> goodsPage = goodsRepository.search(queryBuilder, pageable);
        return goodsPage.getContent();
    }

    /**
     * 模糊搜索
     * @param condition
     * @return
     */
    @RequestMapping("/getGoodsList")
    public List<Goods> getGoodsList(String condition){
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(condition);
        Iterable<Goods> searchResult = goodsRepository.search(builder);
        List<Goods> goodsList = new ArrayList<>();
        Iterator<Goods> iterator = searchResult.iterator();
        while (iterator.hasNext()){
            goodsList.add(iterator.next());
        }
        return goodsList;
    }
}

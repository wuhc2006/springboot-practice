package com.whc.controller;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BookController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/12 18:14
 * @Version 1.0
 */
@RestController
public class BookController {

    @Autowired
    private TransportClient client;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @GetMapping("/get/book/novel")
    public ResponseEntity get(@RequestParam(name = "id", defaultValue = "") String id){
        GetResponse response = this.client.prepareGet("book", "novel", id).get();

        if (!response.isExists()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response.getSource(), HttpStatus.OK);
    }

    /**
     * 添加
     * @param title
     * @param author
     * @param wordCount
     * @param publishDate
     * @return
     * @throws IOException
     */
    @PostMapping("/add/book/novel")
    public ResponseEntity add(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "author") String author,
            @RequestParam(name = "word_count") int wordCount,
            @RequestParam(name = "publish_date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate) throws IOException {

        try{
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("author", author)
                    .field("word_count", wordCount)
                    .field("publish_date", publishDate.getTime())
                    .endObject();

            IndexResponse response = this.client.prepareIndex("book", "novel")
                    .setSource(content)
                    .get();
            return new ResponseEntity(response.getId(), HttpStatus.OK);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/book/novel")
    public ResponseEntity delete(@RequestParam(name = "id") String id){
        DeleteResponse deleteResponse = this.client.prepareDelete("book", "novel", id).get();
        return new ResponseEntity(deleteResponse.getResult().toString(), HttpStatus.OK);
    }

    /**
     * 更新
     * @param id
     * @param title
     * @param author
     * @param wordCount
     * @param publishDate
     * @return
     * @throws IOException
     */
    @PutMapping("update/book/novel")
    public ResponseEntity update(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "word_count", required = false) Integer wordCount,
            @RequestParam(name = "publish_date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate) throws IOException {

        UpdateRequest request = new UpdateRequest("book", "novel", id);
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();

            if (title != null){
                builder.field("title", title);
            }
            if (author != null){
                builder.field("author", author);
            }
            if (wordCount != null){
                builder.field("word_count", wordCount);
            }
            if (publishDate != null){
                builder.field("publish_date", publishDate.getTime());
            }
            builder.endObject();
            request.doc(builder);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            UpdateResponse updateResponse = this.client.update(request).get();
            return new ResponseEntity(updateResponse.getResult().toString(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 复合查询数据
     * @param title
     * @param author
     * @param gtWordCount
     * @param ltWordCount
     * @return
     */
    @PostMapping("query/book/novel")
    public ResponseEntity query(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "gt_word_count", required = false) Integer gtWordCount,
            @RequestParam(name = "lt_word_count", required = false) Integer ltWordCount
    ){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (author != null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("author", author));
        }
        if (title != null){
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", title));
        }

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("word_count").from(gtWordCount);
        if (ltWordCount != null && ltWordCount > 0){
            rangeQueryBuilder.to(ltWordCount);
        }

        boolQueryBuilder.filter(rangeQueryBuilder);
        SearchRequestBuilder searchRequestBuilder = this.client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setFrom(0)
                .setSize(10);
        SearchResponse response = searchRequestBuilder.get();
        List<Map<String, Object>> result = new ArrayList();

        for (SearchHit hit :response.getHits()){
            result.add(hit.getSourceAsMap());
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

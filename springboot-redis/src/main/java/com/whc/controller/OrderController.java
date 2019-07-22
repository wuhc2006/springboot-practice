package com.whc.controller;

import com.whc.domain.ResponseData;
import com.whc.domain.SeckillStatus;
import com.whc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2019/3/10 17:02
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/push")
    public void push(){
        orderService.push();
    }

    @GetMapping("/buy/{userId}")
    public ResponseData buy(@PathVariable String userId){
        if (userId == null || userId.trim().length() == 0){
            userId = "1";
        }
        String goodsId = "1";
        SeckillStatus seckillStatus = orderService.buy(goodsId, userId);
        return new ResponseData(seckillStatus.getStatus(), seckillStatus.getMessage());
    }

    @RequestMapping("/pop")
    public void pop(){
        orderService.pop();
    }
}

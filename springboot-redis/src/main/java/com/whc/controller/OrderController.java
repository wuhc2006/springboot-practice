package com.whc.controller;

import com.whc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/10 17:02
 * @Version 1.0
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/push")
    public void push(){
        orderService.push();
    }

    @RequestMapping("/pop")
    public void pop(){
        orderService.pop();
    }
}

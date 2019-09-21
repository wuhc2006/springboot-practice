package com.whc.model;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private BlockingQueue queue;

    public Producer(BlockingQueue<Goods> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random rd = new Random(10000);
        while (true){
            try {
                Thread.sleep(rd.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Goods goods = new Goods(rd.nextInt(1000), "手机", new BigDecimal("1000.00"));
            queue.offer(goods);
            System.out.println("生产了一个商品:" + goods.toString() + "，当前剩余的商品数：" + queue.size());
        }
    }
}

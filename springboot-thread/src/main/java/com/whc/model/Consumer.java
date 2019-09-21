package com.whc.model;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Goods> queue;

    public Consumer(BlockingQueue<Goods> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Goods goods = queue.take();
                System.out.println("消费了一个商品:" + goods.toString() + "，当前剩余的商品数：" + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

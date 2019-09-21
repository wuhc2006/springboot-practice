package com.whc.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 生产者和消费者模式
 */
public class ModelTest {
    public static void main(String[] args) {
        BlockingQueue<Goods> queue = new LinkedBlockingDeque<>(10);
        Thread t1 = new Thread(new Producer(queue));
        Thread t2 = new Thread(new Consumer(queue));

        t1.start();
        t2.start();
    }
}

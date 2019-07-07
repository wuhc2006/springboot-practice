package com.whc.sync;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Author Administrator
 * @Date 2019/7/7 13:00
 */
public class SynchronousQueueTest {
    public static void main(String[] args) throws Exception{
        BlockingQueue<Integer> queue = new SynchronousQueue<Integer>();
        System.out.print(queue.offer(1) + " ");
        System.out.print(queue.offer(2) + " ");
        System.out.print(queue.offer(3) + " ");
        System.out.print(queue.take() + " ");
        System.out.println(queue.size());
    }
}

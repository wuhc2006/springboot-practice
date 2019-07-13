package com.whc.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 所谓的多线程并发写数据的数据安全问题，是针对同一个对象的属性或者同一个类的类属性进行修改时的数据不一致问题
 * 如果是不同的对象开启的多线程，对同一个变量，它们的数据是隔离的
 *
 * @author Administrator
 * @date 2019/7/11 22:11
 */
public class ConcurrentHashMapTest2 {
    private Map<Integer, Integer> map = new ConcurrentHashMap<>();
    private ReentrantLock lock = new ReentrantLock();

    {
        map.put(1, 1);
    }
    /*static void put(Integer key, Integer value){
        map.put(key, value);
    }*/

    public static void main(String[] args) throws InterruptedException {
        /*ConcurrentHashMapTest2 test = new ConcurrentHashMapTest2();
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread1, before:" + test.map.get(1));
            test.map.put(1, test.map.get(1) + 1);
            System.out.println("Thread1, after:" + test.map.get(1));
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2, before:" + test.map.get(1));
            test.map.put(1, test.map.get(1) + 1);
            System.out.println("Thread2, after:" + test.map.get(1));
        });
        Thread thread3 = new Thread(() -> {
            Integer old = test.map.get(1);
            System.out.println("Thread3, before:" + test.map.get(1));
            test.map.put(1, test.map.get(1) + 1);
            System.out.println("Thread3, after:" + test.map.get(1));
        });
        Thread thread4 = new Thread(() -> {
            Integer old = test.map.get(1);
            System.out.println("Thread4, before:" + old);
            test.map.putIfAbsent(1, );
            System.out.println("Thread4, after:" + test.map.get(1));
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(1000);

        System.out.println(test.map.get(1));*/
        Map<String, Object> map = new ConcurrentHashMap<>();
        System.out.println(map.get("1") == null);
    }
}

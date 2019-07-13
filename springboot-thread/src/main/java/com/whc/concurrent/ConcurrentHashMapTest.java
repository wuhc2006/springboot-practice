package com.whc.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 如果是对不同的对象的属性进行数据更改，不会有并发问题
 *
 * @Author Administrator
 * @Date 2019/7/10 21:26
 */
public class ConcurrentHashMapTest {
    private static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
    private int sum;

    private synchronized void put(Integer key, Integer value) {
        map.put(key, value);
    }

    private synchronized void print() {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + "; value:" + entry.getValue());
        }
    }

    private void add(int interval) {
        sum +=interval;
    }

    private int get(){
        return sum;
    }


    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMapTest test1 = new ConcurrentHashMapTest();
        ConcurrentHashMapTest test2 = new ConcurrentHashMapTest();
        ConcurrentHashMapTest test3 = new ConcurrentHashMapTest();
        ConcurrentHashMapTest test4 = new ConcurrentHashMapTest();

        Thread thread1 = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                System.out.println("我是线程1，原值" + map.get(i));
                test1.put(i, i);
                System.out.println("我是线程1，修改后的值" + map.get(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });

        Thread thread2 = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                System.out.println("我是线程2，原值" + map.get(i));
                test2.put(i, 10 * i);
                System.out.println("我是线程2，修改后的值" + map.get(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });


        Thread thread3 = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                System.out.println("我是线程3，原值" + map.get(i));
                test3.put(i, 100 * i);
                System.out.println("我是线程3，修改后的值" + map.get(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });

        Thread thread4 = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                System.out.println("我是线程4，原值" + map.get(i));
                test4.put(i, 100 * i);
                System.out.println("我是线程4，修改后的值" + map.get(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(12000);
        System.out.println("================================");
        test1.print();
    }
}

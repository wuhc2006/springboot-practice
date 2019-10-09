package com.whc.clone;

import java.util.HashMap;
import java.util.Map;

/**
 * Hashmap中的深复制浅复制问题
 * 从这里可以看出：HashMap中的复制是浅复制，map中维护的就是key的引用，当通过remove方法对其移除时，只是引用移除了，原来的对象还存在。
 * 通过clone方法得到的是对引用值的拷贝:
 * 对象初始值为: whc->java.lang.Object@357246de
 * 添加admin后，克隆对象的值为:admin->java.lang.Object@28f67ac7、whc->java.lang.Object@357246de
 *
 * @author Administrator
 * @date 2019/10/9
 */
public class HashMapClone {
    public static class MyObject{
        private String name;

        public MyObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return ((MyObject) obj).getName().equals(this.getName());
        }
    }

    public static void main(String[] args) {
        HashMap<MyObject, Object> src = new HashMap<>();
        src.put(new MyObject("whc"), new Object());
        System.out.println("----------对象初始值为----------");
        display(src);
        // add
        System.out.println("----------添加admin后，克隆对象的值为----------");
        HashMap<MyObject, Object> clone = (HashMap<MyObject, Object>) src.clone();
        clone.put(new MyObject("admin"), new Object());
        display(clone);
        // remove 不影响src
        clone.remove(new MyObject("whc"));
        System.out.println("----------移除whc后，原对象的值---------- ");
        display(src);
        System.out.println("----------移除whc后，克隆对象的值---------- ");
        display(clone);
    }

    public static void display(Map<MyObject, Object> map) {
        for (Map.Entry<MyObject, Object> e : map.entrySet()) {
            System.out.println(e.getKey().getName() + "->" + e.getValue());
        }
    }
}

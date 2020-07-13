package com.whc.clazz;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * 这两个参数在JAVA1.7及其版本以上被移除了，因为没有永久区的概念了
 * 常量池存在：方法区
 */
public class RuntimeOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i).intern());
        }
    }
}

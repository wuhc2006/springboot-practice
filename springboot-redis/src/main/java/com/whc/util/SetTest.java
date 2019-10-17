package com.whc.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @date 2019/6/4
 */
public class SetTest {
    public static void main(String[] args) {

        Set<String> result = new HashSet<String>();
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
//                add("英雄联盟");
//                add("穿越火线");
//                add("地下城与勇士");
            }
        };

        Set<String> set2 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("地下城与勇士");
                add("魔兽世界");
            }
        };

//        result.clear();
//        result.addAll(set1);
//        result.retainAll(set2);
//        System.out.println("交集：" + result);

//        result.clear();
//        result.addAll(set1);
        set1.removeAll(set2);
        System.out.println("差集：" + set1);

//        result.clear();
//        result.addAll(set1);
//        result.addAll(set2);
//        System.out.println("并集：" + result);

    }
}

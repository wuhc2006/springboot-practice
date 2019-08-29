package com.whc.util;

import java.util.*;

/**
 * @author Administrator
 * @date 2019/8/29 22:30
 */
public class CollectionsUtil {

    public static boolean isEmpty(Collection t) {
        return t == null || t.isEmpty();
    }

    public static boolean isNotEmpty(Collection t) {
        return !isEmpty(t);
    }

    public static Map<String, Object> emptyMap() {
        return Collections.emptyMap();
    }

    public static boolean isEmpty(Map<?, ?> m) {
        return m == null || m.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> m) {
        return !isEmpty(m);
    }

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        List<String> l2 = null;
        List<String> l3 = new ArrayList<>();
        l3.add("");
        Map<String, Object> m1 = null;
        Map<String, Object> m2 = new HashMap<>();
        Map<String, Object> m3 = new HashMap<>();
        m3.put("a", "a");
        System.out.println(CollectionsUtil.isEmpty(l1));
        System.out.println(CollectionsUtil.isEmpty(l2));
        System.out.println(CollectionsUtil.isEmpty(l3));
        System.out.println(CollectionsUtil.isEmpty(m1));
        System.out.println(CollectionsUtil.isEmpty(m2));
        System.out.println(CollectionsUtil.isEmpty(m3));
    }
}

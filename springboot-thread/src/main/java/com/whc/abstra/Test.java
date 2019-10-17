package com.whc.abstra;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 * @date 2019/8/27
 */
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String ss="{\n" +
                "\t\"$id\":701293375374011392,\n" +
                "\t\"$pk\":\"id\",\n" +
                "\t\"alias_name\":{\n" +
                "\t\t\"zh_CN\":\"w\",\n" +
                "\t\t\"zh_TW\":\"w\"\n" +
                "\t},\n" +
                "\t\"createtime\":1566800680000,\n" +
                "\t\"creator\":701125168340428800,\n" +
                "\t\"id\":701293375374011392,\n" +
                "\t\"modifier\":701125168340428800,\n" +
                "\t\"modifytime\":1566800685000,\n" +
                "\t\"name\":\"w\",\n" +
                "\t\"number\":\"w\"\n" +
                "}";
        byte[] buff=ss.getBytes(StandardCharsets.UTF_8);
        int f=buff.length;
        System.out.println(f);
    }
}

package com.whc.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Administrator
 * @date 2019/7/15 20:38
 */
public class ReadFile {
    public static void main(String[] args) {
        File file = new File("E:\\BaiduYunDownload\\分布式服务架构：原理、设计与实战@www.java1234.com.pdf");
        try (InputStream in = new FileInputStream(file)) {
            System.out.printf("文件大小:" + String.valueOf(in.available()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

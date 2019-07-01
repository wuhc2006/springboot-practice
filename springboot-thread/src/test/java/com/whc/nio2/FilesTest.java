package com.whc.nio2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Files工具类的用法
 *
 * @Author Administrator
 * @Date 2019/7/1 22:08
 */
public class FilesTest {
    public static void main(String[] args) throws IOException {
        // 复制文件
        Files.copy(Paths.get("D:\\FilesTest.java"), new FileOutputStream("a.txt"));
        // 获取文件大小
        System.out.println(Files.size(Paths.get("D:\\FilesTest.java")));

        // 列出当前目录的所有文件和子目录
        //Files.list(Paths.get(".")).forEach(path-> System.out.println(path));
    }
}

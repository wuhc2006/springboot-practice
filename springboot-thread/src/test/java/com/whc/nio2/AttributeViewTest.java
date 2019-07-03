package com.whc.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 属性查看
 *
 * @Author Administrator
 * @Date 2019/7/3 21:21
 */
public class AttributeViewTest {

    public static void main(String[] args) throws IOException {
        Path testPath = Paths.get("D:\\FilesTest.java");
        BasicFileAttributeView basicAttr = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicAttr.readAttributes();
        System.out.println(basicFileAttributes.creationTime());
        System.out.println(basicFileAttributes.size());
        System.out.println(basicFileAttributes.lastAccessTime());
    }
}

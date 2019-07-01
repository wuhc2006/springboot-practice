package com.whc.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * JAVA7之后Path的用法
 *
 * @Author Administrator
 * @Date 2019/7/1 22:03
 */
public class PathTest {

    public static void main(String[] args) {
        Path path = Paths.get(".");
        System.out.println("path中包含的路径数量：" + path.getNameCount());
        System.out.println("path的根路径：" + path.getRoot());

        Path absolutePath = path.toAbsolutePath();
        System.out.println("绝对路径：" + absolutePath);
        System.out.println("绝对路径的根路径：" + absolutePath.getRoot());

        // 多个String来构建path
        Path path2 = Paths.get("D:", "nio", "codes");
        System.out.println(path2);
    }
}

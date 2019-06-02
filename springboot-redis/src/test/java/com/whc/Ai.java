package com.whc;

import java.util.Scanner;

/**
 * @ClassName Ai
 * @Description TODO
 * @Author Administrator
 * @Date 2019/5/2 19:03
 * @Version 1.0
 */
public class Ai {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String question = scanner.nextLine();
        String answer = question.replace("你", "我").replace("吗", "").replace("?", "!");
        System.out.println(answer);
    }
}

package com.whc.link;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/7/7 13:13
 */
public class LinkListTest {

    public static void main(String[] args) throws InterruptedException {
        Node tail = new Node(6, null);
        Node node5 = new Node(5, tail);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node head = new Node(1, node2);

        System.out.println(head.toString());
        Node n = Node.removeNode(head, 4);
        System.out.println(n == null ? "empty" : n);

        List<Node> nodes = new ArrayList<>();
        while(true){
            Thread.sleep(10);
            nodes.add(tail);
        }
    }
}

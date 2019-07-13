package com.whc.link;

import com.whc.link.LinkListTest;

/**
 * 单向链表的逆序
 *
 * @Author Administrator
 * @Date 2019/7/8 21:46
 */
public class LinkNodeSort {
    public static void main(String[] args) {
        Node tail = new Node(6, null);
        Node node5 = new Node(5, tail);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node head = new Node(1, node2);

        System.out.println(Node.reverseNode(head).toString());
    }
}

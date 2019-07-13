package com.whc.link;

/**
 * @Author Administrator
 * @Date 2019/7/8 21:54
 */
public class Node {
    Integer value;
    Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    /**
     * 反转链表
     *
     * @param head
     */
    public static Node reverseNode(Node head) {
        if (head.next == null){
            return head;
        }
        /*Node left = head;
        int size = left.getSize();
        Node right = null;
        for (int i = 0; i < size; i++){
            right = right.next;
        }
        while(left != right && left.next.next != right){

        }
        return null;*/
        Node p = head.next;
        Node pre = null;
        while (p != null){
            if (p.next == null){
                p.next = p;
                break;
            }

        }
        return null;
    }

    /**
     * 获取链表的元素
     *
     * @return
     */
    public int getSize() {
        int size = 1;
        Node it = this;
        while (it.next != null) {
            it = it.next;
            size++;
        }
        return size;
    }

    @Override
    public String toString() {
        Node it = this;
        StringBuilder sb = new StringBuilder();
        sb.append(it.value);
        while (it.next != null) {
            sb.append("->");
            it = it.next;
            sb.append(it.value);
        }
        return sb.toString();
    }

    /**
     * 移除倒数第n个结点
     *
     * @param head
     * @param n
     * @return
     */
    public static Node removeNode(Node head, int n) {
        if (n <= 0) {
            return head;
        }
        if (n >= head.getSize()) {
            return null;
        }
        Node left = head;
        Node right = head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        if (right.next == null) {
            head = head.next;
            return head;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return head;
    }
}

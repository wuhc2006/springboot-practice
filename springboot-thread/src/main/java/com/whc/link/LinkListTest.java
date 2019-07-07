package com.whc.link;

/**
 * @Author Administrator
 * @Date 2019/7/7 13:13
 */
public class LinkListTest {
    public static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

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
        public Node removeNode(Node head, int n) {
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


    public static void main(String[] args) {
        Node tail = new Node(6, null);
        Node node5 = new Node(5, tail);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node head = new Node(1, node2);

        System.out.println(head.toString());
        Node n = head.removeNode(head, 4);
        System.out.println(n == null ? "empty" : n);
    }
}

package com.whc.concurrent;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Administrator
 * @date 2019/9/3 21:10
 */
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        // do while保证一直在重试
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        // do while保证一直在重试
        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    private static class Node<E> {
        public final E item;
        public Node<E> next;

        private Node(E item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentStack<Integer> stack = new ConcurrentStack<>();

        Thread t1 = new Thread(() -> {
            Random rd = new Random();
            for (int i = 0; i<100000; i++){
                stack.push(i);
            }
        });

        Thread t2 = new Thread(() -> {
            Random rd = new Random();
            for (int i = 0; i < 100000; i++) {
                stack.push(i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        int count = 0;
        while (stack.top.get() != null) {
            Node<Integer> eNode = stack.top.get();
            System.out.println(eNode.toString());
            stack.pop();
            count++;
        }
        System.out.println("栈的元素个数为:" + count);
    }
}

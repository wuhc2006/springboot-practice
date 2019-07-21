package com.whc.concurrent;

import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/7/14 10:55
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private final List<String> names = Collections.emptyList();

    public List<String> getNames() {
        return names;
    }

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.printf(number + "");
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
        NoVisibility noVisibility = new NoVisibility();
    }
}

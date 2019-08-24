package com.whc.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @date 2019/8/19 20:19
 */
public class UncaughtExceptionTest {
    public static void main(String[] args) {
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(new ExceptionThread());
        } catch (RuntimeException e) {
            System.out.println("Catched Runtime Exception.");
        }
    }
}

class ExceptionThread implements Runnable {
    public void run() {
        throw new RuntimeException();
    }
}

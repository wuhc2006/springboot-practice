package com.whc.soket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ClassName TaskExecutionWebServer
 * @Description 基于线程池的web服务器
 * @Author Administrator
 * @Date 2019/4/7 15:12
 * @Version 1.0
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS = 5;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket conn = socket.accept();
            exec.execute(() -> {
                handleRequest(conn);
            });
        }
    }

    private static void handleRequest(Socket conn) {
        System.out.println(conn.getPort() + " is running......");
    }
}

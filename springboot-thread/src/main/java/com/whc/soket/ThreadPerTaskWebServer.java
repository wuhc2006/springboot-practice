package com.whc.soket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName ThreadPerTaskWebServer
 * @Description 为每个请求创建一个线程
 * @Author Administrator
 * @Date 2019/4/7 15:03
 * @Version 1.0
 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket conn = socket.accept();
            Runnable task = () -> {
                handleRequest(conn);
            };
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket conn) {
        System.out.println(conn.getPort() + " is running...");
    }
}

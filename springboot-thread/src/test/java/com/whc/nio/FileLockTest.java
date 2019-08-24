package com.whc.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 文件锁测试
 *
 * @Author Administrator
 * @Date 2019/7/1 21:56
 */
public class FileLockTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        try(FileChannel channel = new FileOutputStream("a.txt").getChannel()){
            FileLock lock = channel.tryLock();
            TimeUnit.SECONDS.sleep(20);
            lock.release();
        }
    }
}

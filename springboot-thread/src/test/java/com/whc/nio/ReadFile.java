package com.whc.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 分多次读取，避免一次读入过多
 *
 * @Author Administrator
 * @Date 2019/7/1 21:44
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
        try(FileInputStream fis = new FileInputStream("D:\\ReadFile.java");
            FileChannel fileChannel = fis.getChannel()){
            ByteBuffer buffer = ByteBuffer.allocate(256);
            while (fileChannel.read(buffer) != -1){
                buffer.flip();//锁定buffer的空白区
                Charset charset = Charset.forName("utf-8");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer charBuffer = decoder.decode(buffer);
                System.out.println(charBuffer);
                buffer.clear();
            }
        }

    }

}

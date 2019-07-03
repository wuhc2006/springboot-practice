package com.whc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * nio的实质：将FileChannel中的全部数据映射成ByteBuffer
 *
 * @Author Administrator
 * @Date 2019/7/3 21:39
 */
public class FileChannelTest {
    public static void main(String[] args) throws Exception{
        File f = new File("D:\\FilesTest.java");
        try(FileChannel inChannel = new FileInputStream(f).getChannel();
            FileChannel outChannel = new FileOutputStream("a.txt").getChannel()){
            // 将inChannel中的数据映射为buffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            // 将buffer中的数据全部输出
            outChannel.write(buffer);
            // 清除，复原位置
            buffer.clear();
            // 创建解码器
            Charset charset = Charset.forName("UTF-8");
            // 创建解码器对象
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            // 输出
            System.out.println(charBuffer);
        }
    }
}

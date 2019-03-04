package com.whc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @ClassName FileUtil
 * @Description TODO 文件工具类
 * @Author Administrator
 * @Date 2019/3/2 21:59
 * @Version 1.0
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 根据redis的key获取文件输入流
     *
     * @param key
     * @return
     */
    public static InputStream getInputStream(String key) {
        return null;
    }

    /**
     * 将字节数组存放到redis的key
     *
     * @param key
     * @param path
     */
    public static void setFile(String key, String path) {
        byte[] bytes = toByteArray(path);
    }

    /**
     * 根据文件路径，通过io读取为字节数组
     *
     * @param path
     * @return
     */
    private static byte[] toByteArray(String path) {
        if (path == null){
            logger.error("路径为空！");
            return null;
        }
        File f = new File(path);
        if (!f.exists()){
            logger.error("文件不存在！");
            return null;
        }

        BufferedInputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream(f));
            byte[] buffer = new byte[10240];
            int len = 0;
            int i = 0;
            while ((len = in.read()) != -1){
                buffer[i] = (byte) len;
                i++;
            }
            byte[] bytes = new byte[i];
            System.arraycopy(buffer, 0, bytes, 0, i);
            return bytes;
        }catch (FileNotFoundException e){
            logger.error(e.getStackTrace().toString());
        } catch (IOException e) {
            logger.error(e.getStackTrace().toString());
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getStackTrace().toString());
                }
            }
        }

        return null;
    }

}

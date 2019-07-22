package com.whc.service.impl;

import com.whc.conf.jedis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * 将文件缓存到redis或从redis下载文件
 *
 * @author Administrator
 * @date 2019/3/2 21:17
 */
@Service
public class FileService {

    public static final String fileServerPath = "D:\\workspaces\\IdeaProjects\\springboot-practice\\springboot-redis\\fileserver\\";
    private Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param file
     * @param timeout
     */
    public void saveFile(MultipartFile file, int timeout) throws IOException {
        //先上传缓存，再上传文件服务器
        String cacheKey = saveToCache(file, timeout);
        saveTempToServer(cacheKey);
    }

    /**
     * 将文件保存到redis缓存
     *
     * @param file
     * @param timeout 过期时间s
     * @return
     */
    public String saveToCache(MultipartFile file, int timeout) {
        String fileName = file.getOriginalFilename();
        String cacheKey = UUID.randomUUID().toString() + "_" + fileName;
        try (FileInputStream in =(FileInputStream) file.getInputStream() ){
            byte[] bytes = toByteArray(in);
            RedisCache.putObject(cacheKey, bytes, timeout);
            logger.info("上传文件" + fileName + "到redis缓存成功！");
        } catch (IOException e) {
            logger.error("上传文件到缓存失败！");
        }
        return cacheKey;
    }

    /**
     * 将输入流转换为字节数组
     *
     * @param in
     * @return
     * @throws IOException
     */
    private byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * 根据缓存的key值，获取文件存到服务器
     *
     * @param cacheKey
     */
    private void saveTempToServer(String cacheKey) throws IOException {
        String fileName = cacheKey.split("_")[1];
        File dest = new File(fileServerPath + File.separator + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
            dest.createNewFile();
        }
        try(FileOutputStream fos = new FileOutputStream(dest)) {
            byte[] bytes = (byte[]) RedisCache.getObject(cacheKey);
            fos.write(bytes);
            logger.info("上传文件" + fileName + "到" + fileServerPath + "成功！");
        }
    }
}

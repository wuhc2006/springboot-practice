package com.whc.service.impl;

import com.whc.util.FileUtil;
import org.springframework.stereotype.Service;

/**
 * @ClassName FileService
 * @Description TODO 将文件缓存到redis或从redis下载文件
 * @Author Administrator
 * @Date 2019/3/2 21:17
 * @Version 1.0
 */
@Service
public class FileService {

    public void saveFileToRedis(String fileName, String path){
        FileUtil.setFile(fileName, path);
    }


}

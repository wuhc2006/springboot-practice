package com.whc.controller;

import com.whc.service.impl.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传
 *
 * @author Administrator
 * @date 2019/3/2 20:56
 */
@Controller
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file){
        if (file.isEmpty()){
            return "上传文件，请选择文件！";
        }
        try {
            fileService.saveFile(file, 30);
        }catch (IOException e){
            logger.error("上传发生异常！", e);
            return "上传失败！";
        }
        return "上传成功！";
    }


}

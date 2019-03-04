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

import java.io.File;
import java.io.IOException;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/2 20:56
 * @Version 1.0
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
        String fileName = file.getOriginalFilename();
        String filePath = "D:/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传文件" + fileName + "到" + filePath + "成功！");
            // 转为数组
            //fileService.saveFileToRedis(filePath + fileName);
            //redisTemplate.opsForValue().set("file", file.getInputStream().read());
            return "上传成功！";
        } catch (IOException e) {
            logger.error("上传文件失败！");
        }
        return "上传失败！";
    }


}

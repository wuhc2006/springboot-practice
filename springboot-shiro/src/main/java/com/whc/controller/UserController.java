package com.whc.controller;

import com.whc.domain.entity.User;
import com.whc.service.UserService;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName UserController
 * @Description TODO 用户管理
 * @Author Administrator
 * @Date 2018/12/22 22:21
 * @Version 1.0
 */
@Api(value = "/user", tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查找用户", tags = "查找用户")
    @GetMapping("/selectOne")
    public ApiResponseVO<Object> selectOne(Long id){
        return new ApiResponseVO<>(200, "查找成功!", userService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "新增用户", tags = "新增用户")
    @GetMapping("/insertOne")
    public ApiResponseVO<Object> insertOne(String username, String password){
        User user = new User(username, password, new Date());
        this.userService.insertOne(user);
        return new ApiResponseVO<>(200, "新增成功!", user);
    }

    @ApiOperation(value = "修改用户", tags = "修改用户")
    @GetMapping("/update")
    public ApiResponseVO<Object> update(Long id, String username, String password){
        User user = this.userService.selectByPrimaryKey(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setUpdateTime(new Date());
        this.userService.update(user);
        return new ApiResponseVO<>(200, "修改成功!", user);
    }

    @ApiOperation(value = "删除用户", tags = "删除用户")
    @GetMapping("/deleteById")
    public ApiResponseVO<Object> deleteById(Long id){
        this.userService.deleteById(id);
        return new ApiResponseVO<>(200, "删除成功！", id);
    }

}

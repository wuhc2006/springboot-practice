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
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……
        User user = userService.selectByPrimaryKey(id);

        responseVO.setCode(200);
        responseVO.setMsg("查找成功！");
        responseVO.setData(user);
        responseVO.setTotal(0);
        return responseVO;
    }

    @ApiOperation(value = "新增用户", tags = "新增用户")
    @GetMapping("/insertOne")
    public ApiResponseVO<Object> insertOne(String username, String password){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAddTime(new Date());
        this.userService.insertOne(user);

        responseVO.setCode(200);
        responseVO.setMsg("新增成功！");
        responseVO.setData(user);
        responseVO.setTotal(0);
        return responseVO;
    }

    @ApiOperation(value = "修改用户", tags = "修改用户")
    @GetMapping("/update")
    public ApiResponseVO<Object> update(Long id, String username, String password){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……
        User user = this.userService.selectByPrimaryKey(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setUpdateTime(new Date());
        this.userService.update(user);

        responseVO.setCode(200);
        responseVO.setMsg("修改成功！");
        responseVO.setData(user);
        responseVO.setTotal(0);
        return responseVO;
    }

    @ApiOperation(value = "修改用户", tags = "修改用户")
    @GetMapping("/deleteById")
    public ApiResponseVO<Object> deleteById(Long id){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……
        this.userService.deleteById(id);

        responseVO.setCode(200);
        responseVO.setMsg("删除成功！");
        responseVO.setData(null);
        responseVO.setTotal(0);
        return responseVO;
    }

}

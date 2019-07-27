package com.whc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whc.domain.entity.User;
import com.whc.service.UserService;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Administrator
 * @date 2018/12/22 22:21
 */
@Api(value = "/user", tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ApiResponseVO<Object> list(int page, int pageSize, User user) {
        PageHelper.startPage(page, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userService.list(user));
        return new ApiResponseVO<>(200, "查询成功！", pageInfo.getList(), (int)pageInfo.getTotal());
    }

    @ApiOperation(value = "查找用户", tags = "查找用户")
    @GetMapping("/select/{id}")
    public ApiResponseVO<Object> selectOne(@PathVariable Long id){
        return new ApiResponseVO<>(200, "查找成功!", userService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "新增用户", tags = "新增用户")
    @PostMapping("/insert")
    public ApiResponseVO<Object> insertOne(@RequestParam String username,@RequestParam String password){
        User user = new User(username, password, new Date());
        this.userService.insertOne(user);
        return new ApiResponseVO<>(200, "新增成功!", user);
    }

    @ApiOperation(value = "修改用户", tags = "修改用户")
    @PostMapping("/update")
    public ApiResponseVO<Object> update(@RequestParam Long id, @RequestParam String username,@RequestParam String password){
        User user = this.userService.selectByPrimaryKey(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setUpdateTime(new Date());
        this.userService.update(user);
        return new ApiResponseVO<>(200, "修改成功!", user);
    }

    @ApiOperation(value = "删除用户", tags = "删除用户")
    @PostMapping("/delete/{id}")
    public ApiResponseVO<Object> deleteById(@PathVariable Long id){
        this.userService.deleteById(id);
        return new ApiResponseVO<>(200, "删除成功！", id);
    }

}

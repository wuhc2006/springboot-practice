package com.whc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whc.domain.entity.User;
import com.whc.service.UserService;
import com.whc.util.MD5Util;
import com.whc.vo.ApiResponseVO;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Administrator
 * @date 2018/12/22 22:21
 */
@Api(value = "/user", tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ApiResponseVO<Object> list(int page, int pageSize, User user) {
        PageHelper.startPage(page, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userService.list(user));
        return ApiResponseVO.success("查找成功", pageInfo.getList(), (int) pageInfo.getTotal());
    }

    @ApiOperation(value = "查找用户", tags = "查找用户")
    @GetMapping("/select/{id}")
    public ApiResponseVO<Object> selectOne(@PathVariable Long id) {
        return ApiResponseVO.success("查找成功", userService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "新增用户", tags = "新增用户")
    @PostMapping("/insert")
    public ApiResponseVO<Object> insertOne(@NotNull User user) {
        if (StringUtil.isNullOrEmpty(user.getUsername()) || StringUtil.isNullOrEmpty(user.getPassword())) {
            return new ApiResponseVO<>(200, "用户名或密码为空!", user);
        }
        user.setAddTime(new Date());
        user.setUserStatus("1");
        user.setPassword(MD5Util.MD5(user.getPassword()));
        this.userService.insertOne(user);
        return ApiResponseVO.success("新增成功", user);
    }

    @ApiOperation(value = "修改用户", tags = "修改用户")
    @PostMapping("/update")
    public ApiResponseVO<Object> update(@NotNull User user) {
        if (user.getId() == null) {
            return new ApiResponseVO<>(500, "不合法的用户!", user);
        }
        user.setUpdateTime(new Date());
        this.userService.update(user);
        return ApiResponseVO.success("修改成功", user);
    }

    @ApiOperation(value = "修改密码", tags = "修改密码")
    @PostMapping("/update/password")
    public ApiResponseVO<Object> update(@RequestParam Long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        User user = this.userService.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResponseVO.fail(500, "用户不存在!");
        }
        if (!MD5Util.MD5(oldPassword).equals(user.getPassword())){
            return ApiResponseVO.fail(500, "原密码不正确!");
        }
        if (StringUtil.isNullOrEmpty(newPassword)){
            return ApiResponseVO.fail(500, "密码不合法!");
        }
        user.setPassword(MD5Util.MD5(newPassword));
        user.setUpdateTime(new Date());
        this.userService.updatePassword(user);
        return ApiResponseVO.success("修改成功!", user);
    }


    @ApiOperation(value = "删除用户", tags = "删除用户")
    @DeleteMapping("/delete/{id}")
    public ApiResponseVO<Object> deleteById(@PathVariable Long id) {
        this.userService.deleteById(id);
        return ApiResponseVO.success("删除成功", id);
    }
}

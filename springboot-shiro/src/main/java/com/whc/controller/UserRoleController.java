package com.whc.controller;

import com.whc.service.UserRoleService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserRoleController
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/26 22:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 通过user获取角色
     * @return
     */
    @RequestMapping("/getRoleByUser")
    public ApiResponseVO<Object> getRoleByUser(@RequestParam Long userId){
        return new ApiResponseVO<>(200, "查询成功!", userRoleService.selectByUserId(userId));
    }
}

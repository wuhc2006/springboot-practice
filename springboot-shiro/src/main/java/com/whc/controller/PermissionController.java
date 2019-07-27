package com.whc.controller;

import com.whc.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单管理
 *
 * @author Administrator
 * @date 2018/12/26 22:33
 */
@RestController
@RequestMapping("/menu")
@RequiresRoles("admin")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /*@GetMapping("/list")
    public ApiResponseVO<Object> list(){
        return new ApiResponseVO<>(200, "获取菜单成功！", permissionService.selectPermissionsByRole(1l));
    }*/
}

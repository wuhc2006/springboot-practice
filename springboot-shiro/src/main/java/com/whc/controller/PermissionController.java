package com.whc.controller;

import com.whc.domain.entity.Permission;
import com.whc.service.PermissionService;
import com.whc.vo.ApiResponseVO;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MenuController
 * @Description TODO 菜单管理
 * @Author Administrator
 * @Date 2018/12/26 22:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/menu")
@RequiresRoles("admin")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list")
    public ApiResponseVO<Object> list(){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        apiResponseVO.setCode(200);
        apiResponseVO.setMsg("获取菜单成功！");

        List<Permission> permissionList = permissionService.selectPermissionsByRole(1l);
        apiResponseVO.setData(permissionList);
        return apiResponseVO;
    }
}

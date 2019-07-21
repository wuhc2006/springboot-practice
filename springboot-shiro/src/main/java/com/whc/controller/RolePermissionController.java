package com.whc.controller;

import com.whc.domain.entity.RolePermission;
import com.whc.service.RolePermissionService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/26 23:00
 */
@RestController
@RequestMapping("/permission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 通过user获取角色
     * @return
     */
    @RequestMapping("/getRoleByUser")
    public ApiResponseVO<Object> getRoleByUser(@RequestParam Long roleId){
        List<RolePermission> list = rolePermissionService.selectRolePermissionByRole(roleId);
        return new ApiResponseVO<>(200, "查询成功！", list, list.size());
    }

}

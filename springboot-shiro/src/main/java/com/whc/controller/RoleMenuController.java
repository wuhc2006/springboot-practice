package com.whc.controller;

import com.whc.service.RoleMenuService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/1/12 9:51
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 通过角色查询菜单
     * @return
     */
    @GetMapping("/selectMenus")
    public ApiResponseVO<Object> selectMenuByRoleId(@RequestParam Long roleId){
        return new ApiResponseVO<>(200, "查找到菜单", roleMenuService.selectMenuByRoleId(roleId));
    }

    /**
     * 为某个角色添加菜单
     * @return
     */
    @PostMapping("/addMenu2Role")
    public ApiResponseVO<Object> addMenu2Role(Long roleId, List<Long> menuIds){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        apiResponseVO = roleMenuService.addMenu2Role(roleId, menuIds);
        return apiResponseVO;
    }
}

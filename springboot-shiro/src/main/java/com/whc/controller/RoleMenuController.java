package com.whc.controller;

import com.whc.domain.entity.User;
import com.whc.service.RoleMenuService;
import com.whc.service.UserRoleService;
import com.whc.service.UserService;
import com.whc.util.ContextUtil;
import com.whc.util.JwtUtil;
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

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    /**
     * 通过角色查询菜单
     *
     * @return
     */
    @GetMapping("/select/{roleId}")
    public ApiResponseVO<Object> selectMenuByRoleId(@PathVariable String roleId) {
        if (roleId == null) {
            return selectMenuByContext();
        }
        return new ApiResponseVO<>(200, "查找到菜单", roleMenuService.selectMenuByRoleId(Long.parseLong(roleId)));
    }

    @GetMapping("/selectMenuByContext")
    public ApiResponseVO<Object> selectMenuByContext() {
        String username = JwtUtil.getUsername(ContextUtil.get().toString());
        User user = userService.findByName(username);
        if (user != null) {
            return new ApiResponseVO<>(200, "查找到菜单!", roleMenuService.selectMenuByRoleId(user.getId()));
        } else {
            return new ApiResponseVO<>(500, "未找到用户", null);
        }
    }

    /**
     * 为某个角色添加菜单
     *
     * @return
     */
    @PostMapping("/addMenu2Role")
    public ApiResponseVO<Object> addMenu2Role(Long roleId, @RequestParam(value = "menuIds[]") List<Long> menuIds) {
        ApiResponseVO<Object> apiResponseVO = roleMenuService.addMenu2Role(roleId, menuIds);
        return apiResponseVO;
    }
}

package com.whc.controller;

import com.whc.domain.entity.User;
import com.whc.service.RoleMenuService;
import com.whc.service.UserRoleService;
import com.whc.service.UserService;
import com.whc.util.ContextUtil;
import com.whc.util.JwtUtil;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @GetMapping("/selectMenus")
    public ApiResponseVO<Object> selectMenuByRoleId(Long roleId){
        if (roleId == null){
            return selectMenuByContext();
        }
        return new ApiResponseVO<>(200, "查找到菜单", roleMenuService.selectMenuByRoleId(roleId));
    }

    @GetMapping("/selectMenuByContext")
    public ApiResponseVO<Object> selectMenuByContext(){
        String username = JwtUtil.getUsername(ContextUtil.get().toString());
        User user = userService.findByName(username);
        if (user != null){
            return new ApiResponseVO<>(500, "未找到用户!", roleMenuService.selectMenuByRoleId(user.getId()));
        } else{
            return new ApiResponseVO<>(500, "查找到菜单", null);
        }
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

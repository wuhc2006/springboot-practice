package com.whc.controller;

import com.whc.domain.entity.User;
import com.whc.service.UserRoleService;
import com.whc.service.UserService;
import com.whc.util.ContextUtil;
import com.whc.util.JwtUtil;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2018/12/26 22:55
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;

    /**
     * 通过user获取角色
     *
     * @return
     */
    @RequestMapping("/getRoleByUser")
    public ApiResponseVO<Object> getRoleByUser(Long userId) {
        if (userId == null) {
            User user = userService.findByName(JwtUtil.getUsername(ContextUtil.get().toString()));
            if (user != null) {
                return new ApiResponseVO<>(200, "查询成功!", userRoleService.selectByUserId(user.getId()));
            } else {
                return new ApiResponseVO<>(500, "用户不存在!", null);
            }
        }
        return new ApiResponseVO<>(200, "查询成功!", userRoleService.selectByUserId(userId));
    }

}

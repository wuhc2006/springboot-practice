package com.whc.sender;

import com.whc.domain.entity.RolePermission;
import com.whc.service.RolePermissionService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName RolePermission
 * @Description TODO 权限管理
 * @Author Administrator
 * @Date 2018/12/26 23:00
 * @Version 1.0
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
    public ApiResponseVO<Object> getRoleByUser(Long roleId){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        if (null == roleId){
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("输入的参数不合法");
            return apiResponseVO;
        }

        List<RolePermission> list = rolePermissionService.selectRolePermissionByRole(roleId);
        apiResponseVO.setCode(200);
        apiResponseVO.setMsg("查询成功");
        apiResponseVO.setTotal(list.size());
        apiResponseVO.setData(list);

        return apiResponseVO;
    }

}

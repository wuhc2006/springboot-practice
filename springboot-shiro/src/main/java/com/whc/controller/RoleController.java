package com.whc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whc.domain.entity.Role;
import com.whc.service.RoleService;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Administrator
 * @date 2018/12/26 22:31
 */
@Api(value = "/role", tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 列出所有角色
     *
     * @param role     过滤参数
     * @param page     页码
     * @param pageSize 每页大小
     * @return
     */
    @ApiOperation(value = "列出角色", tags = "列出所有角色")
    @GetMapping("/list")
    @RequiresRoles(value = {"admin", "guest"}, logical = Logical.OR)
    public ApiResponseVO<Object> list(Role role, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Role> pageInfo = new PageInfo<>(roleService.list(role));
        return new ApiResponseVO<>(200, "查询成功！", pageInfo.getList(), (int) pageInfo.getTotal());
    }


    @ApiOperation(value = "新增角色", tags = "新增角色")
    @PostMapping("/insertOne")
    @RequiresRoles("admin")
    public ApiResponseVO<Object> insertOne(String name, String title, Integer type) {
        Role role = new Role(name, title, new Date(), type, 1);
        this.roleService.insertOne(role);
        return new ApiResponseVO<>(200, "新增成功!", role);
    }

    @ApiOperation(value = "修改角色", tags = "修改角色")
    @PostMapping("/update")
    @RequiresRoles("admin")
    public ApiResponseVO<Object> update(Long roleId, String name, String title, Integer type) {
        Role role = this.roleService.selectByPrimaryKey(roleId);
        role.setName(name);
        role.setTitle(title);
        role.setType(type);
        role.setUpdateTime(new Date());
        this.roleService.update(role);
        return new ApiResponseVO<>(200, "修改成功!", role);
    }

    @ApiOperation(value = "删除角色", tags = "删除角色")
    @PostMapping("/deleteById")
    @RequiresRoles("admin")
    public ApiResponseVO<Object> deleteById(@RequestParam Long roleId) {
        this.roleService.deleteById(roleId);
        return new ApiResponseVO<>(200, "删除成功!", roleId);
    }

}

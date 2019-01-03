package com.whc.controller;

import com.whc.domain.entity.Role;
import com.whc.service.RoleService;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName RoleController
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/26 22:31
 * @Version 1.0
 */
@Api(value = "/user", tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    /**
     * 列出所有角色
     * @return
     */
    @ApiOperation(value = "列出所有角色", tags = "列出所有角色")
    @RequestMapping("/list")
    public ApiResponseVO<Object> list(){

        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        List<Role> list = roleService.selectAll();

        apiResponseVO.setCode(200);
        apiResponseVO.setMsg("查询成功");
        apiResponseVO.setTotal(list.size());
        apiResponseVO.setData(list);

        return apiResponseVO;
    }


    @ApiOperation(value = "新增角色", tags = "新增角色")
    @GetMapping("/insertOne")
    public ApiResponseVO<Object> insertOne(String username, String password){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……

        Role role = new Role();
        role.setRoleName("系统管理员");
        role.setAddTime(new Date());
        this.roleService.insertOne(role);

        responseVO.setCode(200);
        responseVO.setMsg("新增成功！");
        responseVO.setData(role);
        responseVO.setTotal(0);
        return responseVO;
    }

    @ApiOperation(value = "修改角色", tags = "修改角色")
    @GetMapping("/update")
    public ApiResponseVO<Object> update(Long id, String username, String password){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……
        Role role = this.roleService.selectByPrimaryKey(id);
        role.setRoleName("开发人员");
        role.setUpdateTime(new Date());
        this.roleService.update(role);

        responseVO.setCode(200);
        responseVO.setMsg("修改成功！");
        responseVO.setData(role);
        responseVO.setTotal(0);
        return responseVO;
    }

    @ApiOperation(value = "修改角色", tags = "修改角色")
    @GetMapping("/deleteById")
    public ApiResponseVO<Object> deleteById(Long id){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……
        this.roleService.deleteById(id);

        responseVO.setCode(200);
        responseVO.setMsg("删除成功！");
        responseVO.setData(null);
        responseVO.setTotal(0);
        return responseVO;
    }

}

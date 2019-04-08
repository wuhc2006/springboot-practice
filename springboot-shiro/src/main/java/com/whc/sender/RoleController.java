package com.whc.sender;

import com.whc.domain.entity.Role;
import com.whc.service.RoleService;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleController
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/26 22:31
 * @Version 1.0
 */
@Api(value = "/role", tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 列出所有角色
     * @return
     */
    @ApiOperation(value = "列出角色", tags = "列出所有角色")
    @GetMapping("/list")
    @RequiresRoles("admin")
    public ApiResponseVO<Object> list(String name, String title, Integer type, Integer status){

        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        Map<String, Object> map = new HashMap();
        if (!StringUtils.isEmpty(name)){
            map.put("name", name);
        }
        if (!StringUtils.isEmpty(title)){
            map.put("title", title);
        }
        if (!StringUtils.isEmpty(type)){
            map.put("type", type);
        }
        if (!StringUtils.isEmpty(status)){
            map.put("status", status);
        }
        List<Role> list = roleService.selectAll(map);

        apiResponseVO.setCode(200);
        apiResponseVO.setMsg("查询成功");
        apiResponseVO.setTotal(list.size());
        apiResponseVO.setData(list);

        return apiResponseVO;
    }


    @ApiOperation(value = "新增角色", tags = "新增角色")
    @PostMapping("/insertOne")
    @RequiresRoles("admin")
    public ApiResponseVO<Object> insertOne(String name, String title, Integer type){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……

        Role role = new Role();
        role.setName(name);
        role.setTitle(title);
        role.setType(type);
        role.setStatus(1);
        role.setAddTime(new Date());
        this.roleService.insertOne(role);

        responseVO.setCode(200);
        responseVO.setMsg("新增成功！");
        responseVO.setData(role);
        responseVO.setTotal(0);
        return responseVO;
    }

    @ApiOperation(value = "修改角色", tags = "修改角色")
    @PostMapping("/update")
    @RequiresRoles("admin")
    public ApiResponseVO<Object> update(Long roleId, String name, String title, Integer type){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();

        //try to do something……
        Role role = this.roleService.selectByPrimaryKey(roleId);
        role.setName(name);
        role.setTitle(title);
        role.setType(type);
        role.setUpdateTime(new Date());
        this.roleService.update(role);

        responseVO.setCode(200);
        responseVO.setMsg("修改成功！");
        responseVO.setData(role);
        responseVO.setTotal(0);
        return responseVO;
    }

    @ApiOperation(value = "修改角色", tags = "修改角色")
    @PostMapping("/deleteById")
    @RequiresRoles("admin")
    public ApiResponseVO<Object> deleteById(Long roleId){
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();
        if (roleId == null) {
            return null;
        }
        //try to do something……
        this.roleService.deleteById(roleId);

        responseVO.setCode(200);
        responseVO.setMsg("删除成功！");
        responseVO.setData(null);
        responseVO.setTotal(0);
        return responseVO;
    }

}

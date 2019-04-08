package com.whc.sender;

import com.whc.domain.entity.Menu;
import com.whc.service.RoleMenuService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName RoleMenuController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/12 9:51
 * @Version 1.0
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
    public ApiResponseVO<Object> selectMenuByRoleId(Long roleId){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        if (roleId != null){
            List<Menu> menuList = roleMenuService.selectMenuByRoleId(roleId);
            apiResponseVO.setCode(200);
            apiResponseVO.setMsg("查找到菜单");
            apiResponseVO.setData(menuList);
        }else{
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("输入参数为空");
        }
        return apiResponseVO;
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

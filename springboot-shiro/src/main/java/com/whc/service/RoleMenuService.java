package com.whc.service;

import com.whc.domain.entity.Menu;
import com.whc.domain.entity.RoleMenu;
import com.whc.vo.ApiResponseVO;

import java.util.List;

public interface RoleMenuService {
    int deleteByPrimaryKey(Long roleMenuId);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Long roleMenuId);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    List<Menu> selectMenuByRoleId(Long roleId);

    ApiResponseVO<Object> addMenu2Role(Long roleId, List<Long> menuIds);

    /**
     * 清除角色所拥有的权限
     * @param roleId
     */
    void deleteMenuByRoleId(Long roleId);
}

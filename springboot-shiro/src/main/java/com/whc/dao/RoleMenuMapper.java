package com.whc.dao;

import com.whc.domain.entity.Menu;
import com.whc.domain.entity.RoleMenu;

import java.util.List;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Long roleMenuId);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Long roleMenuId);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    List<Menu> selectMenuByRoleId(Long roleId);

    void deleteMenuByRoleId(Long roleId);
}
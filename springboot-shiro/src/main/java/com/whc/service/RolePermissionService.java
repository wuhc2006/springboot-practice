package com.whc.service;

import com.whc.domain.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    List<RolePermission> selectRolePermissionByRole(Long userId);
}

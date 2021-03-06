package com.whc.service;

import com.whc.domain.entity.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectPermissionsByRole(Long roleId);
}

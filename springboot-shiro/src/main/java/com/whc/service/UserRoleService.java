package com.whc.service;

import com.whc.domain.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> selectByUserId(Long id);

    void assignRole(Long userId, Long roleId);
}

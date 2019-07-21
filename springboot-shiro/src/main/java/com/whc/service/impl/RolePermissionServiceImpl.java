package com.whc.service.impl;

import com.whc.dao.RolePermissionMapper;
import com.whc.domain.entity.RolePermission;
import com.whc.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/26 23:04
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return rolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    @Override
    public int insertSelective(RolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    @Override
    public RolePermission selectByPrimaryKey(Long id) {
        return rolePermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RolePermission record) {
        return rolePermissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RolePermission record) {
        return rolePermissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<RolePermission> selectRolePermissionByRole(Long userId) {
        return rolePermissionMapper.selectRolePermissionByRole(userId);
    }
}

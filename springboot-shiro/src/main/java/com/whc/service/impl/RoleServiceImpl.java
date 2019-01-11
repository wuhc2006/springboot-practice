package com.whc.service.impl;

import com.whc.dao.RoleMapper;
import com.whc.domain.entity.Role;
import com.whc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/22 23:24
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll(Map<String, Object> map) {
        return roleMapper.selectAll(map);
    }

    @Override
    public int insertOne(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int deleteById(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }
}

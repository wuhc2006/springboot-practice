package com.whc.service.impl;

import com.whc.dao.UserRoleMapper;
import com.whc.domain.entity.UserRole;
import com.whc.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/22 23:25
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return userRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserRole record) {
        return userRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(UserRole record) {
        return userRoleMapper.insertSelective(record);
    }

    @Override
    public UserRole selectByPrimaryKey(Long id) {
        return userRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserRole record) {
        return userRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserRole record) {
        return userRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<UserRole> selectByUserId(Long id) {
        return userRoleMapper.selectByUserId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void assignRole(Long userId, Long roleId) {
        // 先查询是否有角色
        List<UserRole> userRoles = userRoleMapper.selectByUserId(userId);
        if (userRoles != null && !userRoles.isEmpty()){
            UserRole userRole = userRoles.get(0);
            userRole.setRoleId(roleId);
            userRoleMapper.updateByPrimaryKey(userRole);// 只能有一个角色
        } else{
            userRoleMapper.insert(new UserRole(userId, roleId));
        }
    }
}

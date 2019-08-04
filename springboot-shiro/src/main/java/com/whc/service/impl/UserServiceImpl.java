package com.whc.service.impl;

import com.whc.dao.UserMapper;
import com.whc.dao.UserRoleMapper;
import com.whc.domain.entity.User;
import com.whc.domain.entity.UserRole;
import com.whc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/22 23:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    /**
     * 新增的用户默认为普通用户
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int insertOne(User user) {
        int i = userMapper.insertOne(user);
        UserRole userRole = new UserRole(user.getId(), 5L);
        int j = userRoleMapper.insert(userRole);
        return i;
    }

    /**
     * 删除角色关系
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int deleteById(Long id) {
        int i = userMapper.deleteById(id);
        userRoleMapper.deleteByUser(id);
        return i;
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public List<User> list(User user) {
        return userMapper.list(user);
    }
}

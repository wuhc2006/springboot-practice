package com.whc.service.impl;

import com.whc.dao.UserMapper;
import com.whc.domain.entity.User;
import com.whc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/22 23:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public boolean insertOne(User user) {
        return userMapper.insertOne(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public boolean update(User user) {
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

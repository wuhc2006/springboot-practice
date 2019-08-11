package com.whc.dao;

import com.whc.domain.entity.User;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/23 10:22
 */
public interface UserMapper {
    User selectByPrimaryKey(Long id);
    List<User> selectAll();
    int insertOne(User user);
    int deleteById(Long id);
    int update(User user);
    User findByName(String username);

    List<User> list(User user);
}

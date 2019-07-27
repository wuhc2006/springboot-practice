package com.whc.dao;

import com.whc.domain.entity.User;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/23 10:22
 * @Version 1.0
 */
public interface UserMapper {
    User selectByPrimaryKey(Long id);
    List<User> selectAll();
    boolean insertOne(User user);
    boolean deleteById(Long id);
    boolean update(User user);
    User findByName(String username);

    List<User> list(User user);
}

package com.whc.service;

import com.whc.domain.entity.User;

import java.util.List;

public interface UserService {

    User selectByPrimaryKey(Long id);
    List<User> selectAll();
    int insertOne(User user);
    int deleteById(Long id);
    int update(User user);
    int updatePassword(User user);
    User findByName(String username);

    List<User> list(User user);

    List<User> listAll();
}

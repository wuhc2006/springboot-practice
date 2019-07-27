package com.whc.service;

import com.whc.domain.entity.User;

import java.util.List;

public interface UserService {

    User selectByPrimaryKey(Long id);
    List<User> selectAll();
    boolean insertOne(User user);
    boolean deleteById(Long id);
    boolean update(User user);
    User findByName(String username);

    List<User> list(User user);
}

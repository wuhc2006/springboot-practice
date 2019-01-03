package com.whc.service;

import com.whc.domain.entity.Role;
import com.whc.domain.entity.User;

import java.util.List;

public interface RoleService {

    Role selectByPrimaryKey(Long id);
    List<Role> selectAll();
    int insertOne(Role role);
    int deleteById(Long id);
    int update(Role role);
}

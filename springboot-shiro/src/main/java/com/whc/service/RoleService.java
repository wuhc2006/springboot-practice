package com.whc.service;

import com.whc.domain.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Role selectByPrimaryKey(Long id);
    List<Role> selectAll(Map<String, Object> map);
    int insertOne(Role role);
    int deleteById(Long id);
    int update(Role role);

    List<Role> list(Role role);
}

package whc.dao;

import whc.entity.Users;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/6/23 17:14
 */
public interface UsersMapper{
    List<Users> findAll();
    void insertOne(Users user);
    Users selectOne(Long id);
}

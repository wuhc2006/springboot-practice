package whc.service;

import org.springframework.stereotype.Service;
import whc.entity.Response;
import whc.entity.Users;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/6/23 17:50
 */
public interface UsersService {
    List<Users> findAll();
    void insertOne(Users user);
    Users selectOne(Long id);
}

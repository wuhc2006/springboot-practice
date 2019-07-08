package whc.service;

import whc.entity.User;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/7/8 19:55
 */
public interface UserJpaService {
    List<User> findAll();
}

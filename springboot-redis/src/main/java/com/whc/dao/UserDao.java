package com.whc.dao;

import com.whc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author Administrator
 * @Date 2019/2/25 22:33
 * @Version 1.0
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findUserById(Long id);
}

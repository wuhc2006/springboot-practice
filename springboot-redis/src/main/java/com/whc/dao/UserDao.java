package com.whc.dao;

import com.whc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 * @date 2019/2/25 22:33
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findUserById(Long id);
}

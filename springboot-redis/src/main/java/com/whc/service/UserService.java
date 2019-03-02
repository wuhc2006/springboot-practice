package com.whc.service;

import com.whc.dao.UserDao;
import com.whc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Administrator
 * @Date 2019/2/27 22:31
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }
}

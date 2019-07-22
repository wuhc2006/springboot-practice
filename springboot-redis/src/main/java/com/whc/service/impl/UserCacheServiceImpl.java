package com.whc.service.impl;

import com.whc.dao.UserDao;
import com.whc.domain.User;
import com.whc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2019/3/3 11:01
 */
@Service("userCacheService")
public class UserCacheServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }
}

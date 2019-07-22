package com.whc.service.impl;

import com.whc.dao.UserDao;
import com.whc.domain.User;
import com.whc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 声明式缓存
 *
 * @author Administrator
 * @date 2019/3/3 13:04
 */
@Service("userAnnoCacheService")
public class UserAnnoCacheServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserAnnoCacheServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(value = "user", key = "'user'.concat(#id.toString())")
    public User findUserById(Long id) {
        logger.info("find user from db........, id = " + id);
        return userDao.findUserById(id);
    }

    @Override
    @CacheEvict(value = "user", key = "'user'.concat(#id.toString())")
    public void deleteUserById(Long id) {
        logger.info("remove user from db.....and remove cache from redis......");
        userDao.deleteById(id);
    }
}

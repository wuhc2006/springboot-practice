package com.whc.service;

import com.whc.dao.UserDao;
import com.whc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2019/2/27 22:31
 * @Version 1.0
 */
public interface UserService {

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    void deleteUserById(Long id);
}

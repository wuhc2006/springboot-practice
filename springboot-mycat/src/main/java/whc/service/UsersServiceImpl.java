package whc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whc.dao.UsersMapper;
import whc.entity.Response;
import whc.entity.Users;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/6/23 17:51
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> findAll() {
        return usersMapper.findAll();
    }

    @Override
    public void insertOne(Users user) {
        usersMapper.insertOne(user);
    }

    @Override
    public Users selectOne(Long id) {
        return usersMapper.selectOne(id);
    }
}

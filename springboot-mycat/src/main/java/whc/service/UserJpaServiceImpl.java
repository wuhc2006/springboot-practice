package whc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whc.entity.User;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/7/8 19:51
 */
@Service
public class UserJpaServiceImpl implements UserJpaService {

    @Override
    public List<User> findAll(){
        return null;
    }
}

package whc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whc.entity.User;
import whc.service.UserJpaService;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2019/7/8 19:43
 */
@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    @Autowired
    private UserJpaService userJpaService;

    @GetMapping("/")
    public List<User> selectAll() {
        return userJpaService.findAll();
    }
}

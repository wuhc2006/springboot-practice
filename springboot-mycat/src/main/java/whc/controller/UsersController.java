package whc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whc.dao.UsersMapper;
import whc.entity.Response;
import whc.entity.Users;
import whc.service.UsersService;

import java.util.List;
import java.util.Random;

/**
 * @Author Administrator
 * @Date 2019/6/23 17:15
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public List<Users> findAll(){
        return usersService.findAll();
    }

    @PostMapping("/{id}")
    public Response insert(@PathVariable Long id){
        Random rnd = new Random();
        Users users = new Users(id, "admin", rnd.nextInt(10), "admin");
        usersService.insertOne(users);
        return new Response(200, "成功", users);
    }

    @GetMapping("/{id}")
    public Response<Users> selectOne(@PathVariable Long id){
        return new Response(200, "成功", usersService.selectOne(id));
    }

    @GetMapping("/test")
    public String test(){
        return "测试成功@!";
    }
}

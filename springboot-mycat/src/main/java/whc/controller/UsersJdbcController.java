package whc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whc.dao.UsersJdbc;
import whc.entity.Response;
import whc.entity.Users;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * jdbc入库查mycat分库分表
 *
 * @Author Administrator
 * @Date 2019/7/7 12:06
 */
@RestController
@RequestMapping("/jdbc")
public class UsersJdbcController {

    @Autowired
    private UsersJdbc usersJdbc;

    @GetMapping("/name/{name}")
    public Response<Users> selectByName(@PathVariable String name) throws Exception {
        String sql = "select * from t_users where name = ?";
        List<Object> params = Arrays.asList(name);
        return new Response(200, "success", usersJdbc.executeQuery(sql, params));
    }

    @PostMapping("/{id}")
    public Response insert(@PathVariable Long id) throws Exception{
        Random rnd = new Random();
        Users users = new Users(id, "a", rnd.nextInt(20), "@qq.com");
        List<Object> params = Arrays.asList(users.getId(), users.getName(), users.getAge(), users.getEmail());
        String sql = "insert into t_users(id, name, age, email) values(?,?,?,?)";
        usersJdbc.executeUpdate(sql, params);
        return new Response(200, "成功", users);
    }
}
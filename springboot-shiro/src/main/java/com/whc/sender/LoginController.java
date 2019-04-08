package com.whc.sender;

import com.alibaba.druid.util.StringUtils;
import com.whc.domain.entity.User;
import com.whc.service.UserService;
import com.whc.util.JwtToken;
import com.whc.util.JwtUtil;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/22 22:21
 * @Version 1.0
 */
@Api(value = "用户登录管理", tags = "用户登录管理")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", tags = "登录")
    @RequestMapping("/login")
    @ResponseBody
    public ApiResponseVO<Object> loginSuccess(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("username") String username, @RequestParam("password") String password){

        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("用户名或密码为空");
            return apiResponseVO;
        }

        User user = userService.findByName(username);
        if (!user.getPassword().equals(password)){
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("密码错误！");
            return apiResponseVO;
        }

        //构建jwttoken
        String token = JwtUtil.sign(username, password);
        JwtToken jwtToken = new JwtToken(token);

        //在这里验证登录
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(jwtToken);
        }catch (UnknownAccountException exception) {
            apiResponseVO.setMsg("账号不存在");
        } catch (IncorrectCredentialsException exception) {
            apiResponseVO.setMsg("错误的凭证，用户名或密码不正确");
        } catch (LockedAccountException exception) {
            apiResponseVO.setMsg("账户已锁定");
        } catch (ExcessiveAttemptsException exception) {
            apiResponseVO.setMsg("错误次数过多");
        } catch (AuthenticationException exception) {
            apiResponseVO.setMsg("认证失败");
        }

        //认证通过，将token写到cookie
        if(subject.isAuthenticated()){
            Cookie cookie = new Cookie("token",token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600*5);
            cookie.setPath("/");
            response.addCookie(cookie);

            apiResponseVO.setCode(200);
            apiResponseVO.setMsg("success");
            apiResponseVO.setData(token);
            return apiResponseVO;
        }

        return apiResponseVO;
    }


    /**
     * 如果不输入具体地址，默认到转到登录界面
     * @return
     */
    @ApiOperation(value = "任意界面", tags = "任意界面")
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    /**
     * 主页
     * @return
     */
    @ApiOperation(value = "主页", tags = "主页")
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("username", JwtUtil.getUsername((String)SecurityUtils.getSubject().getPrincipal()));
        //加载菜单
        List<Map<String, Object>> menuList = new ArrayList();
        for (int i = 0; i < 3; i++){
            Map<String, Object> menu = new HashMap<>();
            menu.put("id", i + 1);
            menu.put("name", "菜单管理"+i);
            menu.put("href", "http://www.baidu.com");
            menuList.add(menu);
        }
        model.addAttribute("menuList", menuList);
        return "index";
    }

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "退出", tags = "主页")
    @PostMapping("/logout")
    @ResponseBody
    public ApiResponseVO<Object> logout(HttpServletRequest request, HttpServletResponse response){

        SecurityUtils.getSubject().logout();
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();
        responseVO.setCode(200);
        responseVO.setMsg("退出成功！");

        return responseVO;
    }
}

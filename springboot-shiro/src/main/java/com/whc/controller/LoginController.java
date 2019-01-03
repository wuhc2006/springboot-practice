package com.whc.controller;

import com.alibaba.druid.util.StringUtils;
import com.whc.service.UserService;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String loginSuccess(Model model, HttpServletRequest request, HttpServletResponse response, String username, String password){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return "login";
        }
        //try to do something……
        model.addAttribute("username",username);
        model.addAttribute("password",password);

        //在这里验证登录
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            Session session = subject.getSession();
            request.setAttribute("SESSION_USER", subject);
            request.setAttribute("SESSION_TS",System.currentTimeMillis());

            //写jwt

        }

        subject.login(token);

        return "redirect:/index";
    }


    @ApiOperation(value = "任意界面", tags = "任意界面")
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @ApiOperation(value = "主页", tags = "主页")
    @RequestMapping("/index")
    @RequiresPermissions("add")
    public String index(){
        return "index";
    }

    @ApiOperation(value = "退出", tags = "主页")
    @RequestMapping("/logout")
    @ResponseBody
    public ApiResponseVO<Object> logout(){

        //try to do something……
        ApiResponseVO<Object> responseVO = new ApiResponseVO<>();
        responseVO.setCode(200);
        return responseVO;
    }
}

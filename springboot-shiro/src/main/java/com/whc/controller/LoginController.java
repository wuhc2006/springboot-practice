package com.whc.controller;

import com.alibaba.druid.util.StringUtils;
import com.whc.domain.entity.User;
import com.whc.service.MenuService;
import com.whc.service.UserService;
import com.whc.util.ContextUtil;
import com.whc.util.JwtToken;
import com.whc.util.JwtUtil;
import com.whc.util.MD5Util;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 * @date 2018/12/22 22:21
 */
@Api(value = "用户登录管理", tags = "用户登录管理")
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "登录", tags = "登录")
    @RequestMapping("/login")
    @ResponseBody
    public ApiResponseVO<Object> loginSuccess(HttpServletRequest request, HttpServletResponse response,
                               String username, String password) throws IOException {
        if (ContextUtil.get() != null){
            response.sendRedirect("/index");
            return new ApiResponseVO<>(200, "您已经登录!", null);
        }

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return new ApiResponseVO<>(500, "用户名或密码为空！", null);
        }

        User user = userService.findByName(username);
        if (user == null){
            return new ApiResponseVO<>(500, "用户不存在！", null);
        }
        String md5 = MD5Util.MD5(password);
        if (!user.getPassword().equals(md5)){
            return new ApiResponseVO<>(500, "密码错误！", null);
        }

        //构建jwt token
        String token = JwtUtil.sign(username, md5);
        JwtToken jwtToken = new JwtToken(token);

        //在这里验证登录
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
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
            cookie.setMaxAge(60);// cookie有效期5个小时
            cookie.setPath("/");
            response.addCookie(cookie);

            apiResponseVO.setCode(200);
            apiResponseVO.setMsg("success");
            apiResponseVO.setData(token);
            logger.info("登录成功！{}，登录时间：{}", apiResponseVO, new SimpleDateFormat("yyy-MM-dd hh:mm:ss").format(new Date()));
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

    @ApiOperation(value = "未授权界面", tags = "未授权界面")
    @RequestMapping("/403")
    public String unAuthorizated(){
        return "401";
    }


    /**
     * 主页
     * @return
     */
    @ApiOperation(value = "主页", tags = "主页")
    @RequestMapping("/index")
    public String index(Model model){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal == null){
            return "login";
        }
        String username = JwtUtil.getUsername((String) principal);
        model.addAttribute("username", username);
        User user = userService.findByName(username);
        model.addAttribute("menuList", menuService.selectByUserId(user.getId()));
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
        return new ApiResponseVO<>(200, "退出成功!", null);
    }
}

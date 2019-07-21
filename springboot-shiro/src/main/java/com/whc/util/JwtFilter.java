package com.whc.util;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt过滤器来作为shiro的过滤器
 *
 * @author Administrator
 * @date 2019/1/5 16:45
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断请求的头上是否带上token
        if (isLoginAttempt(request, response)){
            try {
                executeLogin(request, response);
            }catch (Exception e){
                //非法请求转入401
                response401(request, response);
            }
        }
        return true;
    }

    /**
     * 判断用户是否想要登入，检查head里面是否有token字段就可以
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("token");
        return authorization != null;
    }

    /**
     * 执行登录
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");

        JwtToken jwtToken = new JwtToken(token);
        //提交给realm进行登录，如果错误会抛出异常并捕获
        getSubject(request, response).login(jwtToken);
        //如果没有抛出异常，则登录成功
        return true;
    }

    /**
     * 支持跨域请求
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));

        //跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);

    }

    /**
     * 非法请求跳转到401
     * @param request
     * @param response
     */
    private void response401(ServletRequest request, ServletResponse response) {
        try{
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect("/");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}

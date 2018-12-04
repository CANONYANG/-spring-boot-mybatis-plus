package com.njbandou.web.interceptor;


import com.njbandou.web.JWTUtils;
import com.njbandou.web.annotation.Authorization;
import com.njbandou.web.constant.AdminConstant;
import com.njbandou.web.exception.AccessDeniedException;
import com.njbandou.web.exception.TokenExpireException;
import com.njbandou.web.redis.JWTRedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Author: CANONYANG
 * Date: 2018/11/26
 * Describe: 拦截器：登录token校验
 * 写这段代码的时候，只有上帝和我知道它是干嘛的
 * 现在，只有上帝知道
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private JWTRedisDAO jwtRedisDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //没有@Authorization注解直接通过
        if (method.getAnnotation(Authorization.class) == null) {
            return true;
        }

        //从header中得到token
        String authorization = request.getHeader(AdminConstant.AUTHORIZATION);

        //这一步一般在前端已经过滤
        if (authorization == null){
            throw new AccessDeniedException();
        }

        //验证token真伪
        Map map = JWTUtils.getClaims(AdminConstant.JWT_SECRET, authorization);
        if (map == null){
            throw new AccessDeniedException();
        }

        //从Redis读取token
        String username = String.valueOf(map.get(AdminConstant.USERNAME));
        if (!jwtRedisDAO.checkToken(username, authorization)){
            throw new TokenExpireException();
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }

}

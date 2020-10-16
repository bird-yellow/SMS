package com.sun.Interceptor;

import com.sun.Utils.Consts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//定义用户登录拦截器
public class LoginInterceptor implements HandlerInterceptor {
//    方法执行前进行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if(request.getSession().getAttribute(Consts.USER) != null){
                return true;
            }
            request.getRequestDispatcher("/WEB-INF/view/login/uLogin/jsp").forward(request,response);
            return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

package com.sun.Utils;

//定义日志工具



import com.sun.Entity.Manage;
import com.sun.Entity.Student;
import com.sun.Entity.Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogUtils {
//    从ThreadLocal中获取request

        public static HttpServletRequest getRequest(){
            return HttpContext.getRequest();
        }

//        获取IP地址
        public static String getIpAddress() {
                HttpServletRequest request = getRequest();
                String ip = request.getHeader("x-forwarded-for");
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
                return ip;
         }

//         获取用户的类型
        public static Object getUserType(){
            HttpSession session = getRequest().getSession();
            return session.getAttribute(Consts.USERTYPE);
        }

        public static  Object getUser(){
                HttpSession session = HttpContext.getSession();
                return session.getAttribute(Consts.USER);
        }

}

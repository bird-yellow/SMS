package com.sun.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HttpContext {

    /// 存储请求
    private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
    //本地线程存储 response
    private  static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();


    public static void  setRequest(HttpServletRequest request){
        requestLocal.set(request);
    }
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest)requestLocal.get();
    }

    public static void setResponse(HttpServletResponse response){
        responseLocal.set(response);
    }
    public static HttpServletResponse getResponse(){
        return responseLocal.get();
    }

    public  static HttpSession getSession(){
        return (HttpSession)((HttpServletRequest)requestLocal.get()).getSession();
    }


}

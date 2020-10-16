package com.sun.Filter;

import com.sun.Utils.HttpContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpContext.setRequest((HttpServletRequest)servletRequest);
        HttpContext.setResponse((HttpServletResponse)servletResponse);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}

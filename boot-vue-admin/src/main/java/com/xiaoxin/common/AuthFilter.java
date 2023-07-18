package com.xiaoxin.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author:XIAOXIN
 * @date:2023/07/17
 **/
public class AuthFilter implements Filter {
    //    放行数组，设置白名单，应该放行的请求
//   在这里如果对请求也做过滤，那么就没办法发送亲求
    private static final String[] WHITE_URLS = {"/login.html", "/register.html", "/user/login", "/user/register"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        if (Arrays.stream(WHITE_URLS).anyMatch(url -> url.equals(servletPath))) {  // java8 Stream API
            filterChain.doFilter(request, response);  // 放行请求
        } else {
            Object user = request.getSession().getAttribute("user");
            if (user != null) {
                filterChain.doFilter(request, response);  // 放行请求
            } else {
                response.sendRedirect("/login.html");
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

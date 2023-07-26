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
//   在这里如果对请求也做过滤，那么就没办法发送亲求,同时请求的资源也会放行
    private static final String[] WHITE_URLS = {"/login.html", "/register.html", "/user/login", "/user/register"};
//    文件后缀加载白名单
    private static final String[] FILE_SUFFIX = {"jpeg", "jpg","png","gif","bmp", "webp", "css","js", "woff", "woff2"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        让这些对象变成处理客户端请求，生成响应，同时和客户端进行交互
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        if (Arrays.stream(WHITE_URLS).anyMatch(url -> url.equals(servletPath))|| endWith(servletPath)) {  // java8 Stream API
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
//    判断给定的文件路径是否以固定的后缀结尾
    private boolean endWith(String path) {
        for (String fileSuffix : FILE_SUFFIX) {
            if (path.endsWith(fileSuffix)) {
                return true;
            }
        }
        return false;
    }
}

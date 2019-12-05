package com.hcx.springbootweb.component.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName MyFilter
 * @Description TODO
 * @Author 贺楚翔
 * @Date 2019-12-05 16:00
 * @Version 1.0
 **/
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter Processing....");
        chain.doFilter(request,response);
    }


    @Override
    public void destroy() {

    }
}

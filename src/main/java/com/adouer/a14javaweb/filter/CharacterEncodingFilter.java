package com.adouer.a14javaweb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = "charSet", value = "UTF-8")})
public class CharacterEncodingFilter implements Filter {

    private String charSet;

    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter,init()");
        charSet = fConfig.getInitParameter("charSet");

    }

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("CharacterEncodingFiltery预处理");
        request.setCharacterEncoding(charSet);
        // pass the request along the filter chain
        chain.doFilter(request, response);
        System.out.println("CharacterEncodingFilter成功");
    }
}

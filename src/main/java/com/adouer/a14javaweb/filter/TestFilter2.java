package com.adouer.a14javaweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 拦截所有jsp页面，doFilter添加逻辑
 */
@WebFilter(urlPatterns = {"*.jsp"})
public class TestFilter2 implements Filter {

	public TestFilter2() {
		System.out.println("TestFilter2被创建。。。" + this);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("TestFilter2被创建init()方法被调用。。。");
	}

	public void destroy() {
		System.out.println("destroy()方法被调用。。。");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//预处理
		System.out.println("before TestFilter2 。。。" + this);

		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("uri=" + req.getRequestURI());

		// pass the request along the filter chain
		chain.doFilter(request, response);

		//后处理
		System.out.println("after TestFilter2 。。。" + this);
	}
}

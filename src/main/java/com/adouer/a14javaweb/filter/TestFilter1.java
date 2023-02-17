package com.adouer.a14javaweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 不写注解，不生效
 *
 */
@WebFilter(urlPatterns = "/*")
public class TestFilter1 implements Filter {

	public TestFilter1() {
		System.out.println("TestFilter1被创建。。。" + this);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init()方法被调用。。。");
	}

	public void destroy() {
		System.out.println("destroy()方法被调用。。。");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//预处理
		System.out.println("before TestFilter1 。。。" + this);

		//HttpServletRequest req = (HttpServletRequest)request;
		//System.out.println("uri=" + req.getRequestURI());

		// pass the request along the filter chain
		chain.doFilter(request, response);

		//后处理
		System.out.println("after TestFilter1 。。。" + this);
	}




}

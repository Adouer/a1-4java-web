package com.adouer.a14javaweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="TestServlet",urlPatterns={"/TestServlet"},loadOnStartup=1,initParams={@WebInitParam(name="myName", value="myValue")})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//构造方法
	public TestServlet(){
		System.out.println("TestServlet被创建。。。");
	}

	//初始化方法
	@Override
	public void init() throws ServletException {
		System.out.println("init()方法被调用。。。");
	}


	//销毁方法
	@Override
	public void destroy() {
		System.out.println("destroy()方法被调用。。。");
	}

	//服务方法
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service()方法被调用。。。");
		super.service(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.getWriter().append("Served at: ").append(request.getContextPath());

		System.out.println("doGet()方法被调用。。。" + this);

		System.out.println("myName=" + this.getInitParameter("myName"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		System.out.println("doPost()方法被调用。。。" + this);
	}

}

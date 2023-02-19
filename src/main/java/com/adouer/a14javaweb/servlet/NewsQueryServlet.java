package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.service.NewsService;
import com.adouer.a14javaweb.service.impl.NewsServiceImpl;
import com.adouer.a14javaweb.vo.NewsPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "NewsQueryServlet", value = "/NewsQueryServlet",initParams = {@WebInitParam(name = "pageSize",value = "10")})
public class NewsQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//接收当前页码
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		//读取分页大小
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));

		//组合业务对象
		NewsService newsService = new NewsServiceImpl();

		//调用业务方法---查询新闻的列表
		NewsPage newsPage = newsService.getNewsPage(currentPage, pageSize);

		//在request属性范围中保存列表
		request.setAttribute("newsPage", newsPage);

		//转发到新闻查询页面
		request.getRequestDispatcher("news_query.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

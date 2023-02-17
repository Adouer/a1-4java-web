package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.entity.NewsType;
import com.adouer.a14javaweb.service.NewsTypeService;
import com.adouer.a14javaweb.service.impl.NewsTypeServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NewsInitServlet", value = "/NewsInitServlet")
public class NewsInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {

		//组合业务对象
		NewsTypeService newsTypeService = new NewsTypeServiceImpl();

		//调用业务方法---查询新闻类型的列表
		List<NewsType> newsTypeList = newsTypeService.getNewsTypeList();

		//在request属性范围中保存列表
		//request.setAttribute("newsTypeList", newsTypeList);

		//获取application对象
		ServletContext application = this.getServletContext();

		//在application属性范围中保存新闻类型列表
		application.setAttribute("newsTypeList", newsTypeList);

		//转发到新闻添加页面
		//request.getRequestDispatcher("news_add.jsp").forward(request, response);

		System.out.println("NewsInitServlet被创建,新闻类型列表读取成功。。。");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

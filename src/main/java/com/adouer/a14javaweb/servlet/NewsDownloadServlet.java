package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.service.NewsService;
import com.adouer.a14javaweb.service.impl.NewsServiceImpl;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "NewsDownloadServlet", value = "/NewsDownloadServlet")
public class NewsDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//接收数据
		String attachment = request.getParameter("attachment");
		String truename = request.getParameter("truename");

		//获取真实路径
		String filepath = request.getServletContext().getRealPath("/attachment");

		//步骤1---创建上传组件对象
		SmartUpload smartUpload = new SmartUpload("utf-8");

		//步骤2---初始化上传组件对象
		smartUpload.initialize(getServletConfig(), request, response);

		//步骤3---设置对内容的处理方式---阻止浏览直接显示文件内容
		smartUpload.setContentDisposition(null);

		//步骤4---下载文件
		try {
			smartUpload.downloadFile(filepath + "/" + attachment, null, truename);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		//****************附件下载的业务处理******************

		//接收下载积分和新闻编号
		int downloadscore = Integer.parseInt(request.getParameter("downloadscore"));
		int newsid = Integer.parseInt(request.getParameter("newsid"));

		//获取session对象
		HttpSession session = request.getSession();

		//获取当前登录用户
		User user = (User)session.getAttribute("user");

		//组合业务对象
		NewsService newsService = new NewsServiceImpl();

		//调用业务方法
		newsService.downloadAttachment(user, newsid, downloadscore);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

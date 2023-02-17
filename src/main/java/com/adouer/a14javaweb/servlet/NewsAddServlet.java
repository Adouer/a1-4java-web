package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.entity.News;
import com.adouer.a14javaweb.entity.NewsType;
import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.service.NewsService;
import com.adouer.a14javaweb.service.impl.NewsServiceImpl;
import com.adouer.a14javaweb.util.StringUtil;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
@WebServlet(name = "NewsAddServlet", value = "/NewsAddServlet")
public class NewsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		//获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();

		//从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		//设置字符编码
		//request.setCharacterEncoding(charSet);


		//****************文件上传开始********************************

		//从配置文件中读取文件大小和允许的类型
		int maxFileSize = Integer.parseInt(this.getInitParameter("maxFileSize"));
		String deniedFileList = this.getInitParameter("deniedFileList");

		String attachment = "";
		String truename = "";
		int downloadscore = 0;

		//步骤1---创建上传组件---实例化SmartUpload对象
		SmartUpload smartUpload = new SmartUpload(charSet);

		//步骤2---初始化上传组件---调用initialize()方法
		smartUpload.initialize(getServletConfig(), request, response);

		try {
			//检查2---文件大小
			smartUpload.setMaxFileSize(maxFileSize*1024*1024);

			//检查3---文件类型
			smartUpload.setDeniedFilesList(deniedFileList);

			//步骤3---上传文件到服务器的临时内存中---调用upload()方法
			smartUpload.upload();

			//步骤4---提取上传文件
			SmartFile smartFile = smartUpload.getFiles().getFile(0);

			//检查1---文件非空
			if(!smartFile.isMissing()){

				//步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
				String serverFilePath = this.getServletContext().getRealPath("/attachment");

				//自动创建目录
				File dir = new File(serverFilePath);
				if(!dir.exists()){
					dir.mkdirs();
				}

				//生成新的文件名
				String serverFilename = StringUtil.convertFilename(smartFile.getFileName());   //原始文件名

				//步骤6---保存上传文件
				smartFile.saveAs(serverFilePath + "/" + serverFilename);

				//更新attachment变量的值为新的文件名
				attachment = serverFilename;

				//原始文件名
				truename = smartFile.getFileName();

				//下载的扣除积分
				downloadscore = Integer.parseInt(smartUpload.getRequest().getParameter("downloadscore"));
			}

		} catch (SmartUploadException e) {
			e.printStackTrace();
			out.println("<script>alert('文件上传失败');history.back()</script>");
		} catch (SecurityException e) {
			e.printStackTrace();
			out.println("<script>alert('文件大小不能超过" + maxFileSize + "M,文件类型不能是" + deniedFileList + "');history.back()</script>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//****************文件上传结束********************************


		//接收数据
		int typeid = Integer.parseInt(smartUpload.getRequest().getParameter("typeid"));
		String title = smartUpload.getRequest().getParameter("title");
		String content = smartUpload.getRequest().getParameter("content");

		//读取添加新闻成功的加分
		int addNewsScore = Integer.parseInt(this.getInitParameter("addNewsScore"));

		//从session属性范围中获取当前用户对象
		User user = (User)session.getAttribute("user");

		//创建并填充news实体类
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		news.setPubtime(new Date());

		//news.setTypeid(typeid);

		//news对象中组合了newstype对象
		NewsType newsType = new NewsType();
		newsType.setTypeid(typeid);
		news.setNewsType(newsType);


		//news.setUserid(user.getUserid());
		news.setUser(user);

		news.setAttachment(attachment);
		news.setTruename(truename);
		news.setDownloadscore(downloadscore);

		//下载次数默认为0
		news.setDownloadcount(0);


		//组合业务对象
		NewsService newsService = new NewsServiceImpl();

		//调用业务方法---添加新闻
		if(newsService.addNews(news, user, addNewsScore)){
			out.println("<script>if(confirm('新闻添加成功，是否继续添加')){location='news_add.jsp'}else{location='news_query.jsp'}</script>");
		}else{
			out.println("<script>alert('新闻添加失败，请重新输入');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

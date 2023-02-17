package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.service.UserService;
import com.adouer.a14javaweb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserQueryServlet", value = "/UserQueryServlet")
public class UserQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	/*	//从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		//设置字符编码
		request.setCharacterEncoding(charSet); */

		//接收用户名
		String username = request.getParameter("username");
		if(username==null){
			username = "";
		}

		//接收用户性别
		String gender = request.getParameter("gender");
		if(gender==null){
			gender = "";
		}

		//接收用户职业
		String job = request.getParameter("job");
		if(job==null){
			job = "";
		}

		//接收注册时间的下拉框的值
		String regtime = request.getParameter("regtime");
		if(regtime==null){
			regtime = "全部时间";
		}

		//选择了指定时间，接收收时间段
		String begintime = "";
		String endtime = "";
		if("指定时间".equals(regtime)){
			begintime = request.getParameter("begintime");
			endtime = request.getParameter("endtime");
		}



		//组合业务层对象
		UserService userService = new UserServiceImpl();

		//调用业务方法
		List<User> list = userService.getUserList(username, gender, job, begintime, endtime);

		//将用户列表保存在request属性范围中
		request.setAttribute("list", list);

		//将用户名保存在request属性范围中
		request.setAttribute("username", username);

		//将用户性别保存在request属性范围中
		request.setAttribute("gender", gender);

		//将用户职业保存在request属性范围中
		request.setAttribute("job", job);

		//将用户注册时间保存在request属性范围中
		request.setAttribute("regtime", regtime);
		request.setAttribute("begintime", begintime);
		request.setAttribute("endtime", endtime);


		//转发到用户查询页面
		request.getRequestDispatcher("user_query.jsp").forward(request, response);;


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

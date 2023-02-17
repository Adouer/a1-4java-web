package com.adouer.a14javaweb.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "UserLogoutServlet", value = "/UserLogoutServlet")
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();

		//获取application对象
		ServletContext application = this.getServletContext();


		//删除session属性范围中的用户对象
		session.removeAttribute("user");

		//更新在线人数-1
		application.setAttribute("onlineCount", (Integer)application.getAttribute("onlineCount")-1);


		//删除cookie
		//CookieUtil.deleteCookie(response, "userInfo");

		//重定向到登录页面
		response.sendRedirect("user_login.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.service.UserService;
import com.adouer.a14javaweb.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserDeleteBatchServlet", value = "/UserDeleteBatchServlet")
public class UserDeleteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		//接收多个用户编号
		String[] userids = request.getParameterValues("userids");

		//检查选中用户的数量
		if(userids==null){
			out.println("<script>alert('server:至少要选择一个用户');history.back()</script>");
			return;
		}

		//组合业务层对象
		UserService userService = new UserServiceImpl();

		//调用业务方法
		if(userService.deleteUserBatch(userids)){
			out.println("<script>alert('用户批量删除成功');location='UserQueryServlet'</script>");
		}else{
			out.println("<script>alert('用户批量删除失败');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

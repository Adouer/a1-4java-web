package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.service.UserService;
import com.adouer.a14javaweb.service.impl.UserServiceImpl;
import com.adouer.a14javaweb.util.CookieUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "UserLoginServlet", value = "/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		//获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();

		//获取application对象
		ServletContext application = this.getServletContext();

	/*	//从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		//设置字符编码
		request.setCharacterEncoding(charSet); */

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String member = request.getParameter("member");
		String autoLogin = request.getParameter("autoLogin");

		//读取登录加分
		int loginScore = Integer.parseInt(this.getInitParameter("loginScore"));

		//组合业务层对象
		UserService useService = new UserServiceImpl();

		//调用业务方法
		User user = useService.login(username, password, loginScore);

		if(user!=null){   //登录成功

			//登录成功并且选中复选框时，发送一个持久化cookie

			//if(member!=null){    //选中复选框
			if(autoLogin!=null){    //选中复选框
				//发送一个持久化cookie
				CookieUtil.addCookie(response, "userInfo", username + "#" + password, 7);
			}

			//在session属性范围中保存用户对象
			session.setAttribute("user", user);

			//在application属性范围中更新并保存在线人数
			if(application.getAttribute("onlineCount")==null){  //第一位访客
				application.setAttribute("onlineCount", 1);
			}else{
				application.setAttribute("onlineCount", (Integer)application.getAttribute("onlineCount")+1);
			}

			//从session属性范围中获取要返回的URL,默认返回主页
			String prevURL = "index.jsp";

			if(session.getAttribute("prevURL")!=null){
				prevURL = (String)session.getAttribute("prevURL");
			}

			//重定向到url
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + prevURL));
		}else{
			out.println("<script>alert('用户名或密码错误，请重新输入');history.back()</script>");   //回退到前一页，之前的数据可以保留
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

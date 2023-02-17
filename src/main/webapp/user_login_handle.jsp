<%@page import="com.adouer.a14javaweb.entity.User"%>
<%@page import="com.adouer.a14javaweb.dbutil.DBManager"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	request.setCharacterEncoding("utf-8");
	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	DBManager dbManager = DBManager.getInstance();
	
	String sql = "select * from user where username = ? and password = ?";
	
	ResultSet rs = dbManager.execQuery(sql, username, password);
	
	if(rs.next()){   
		
		//获取用户积分
		int score = rs.getInt(4);
		
		//获取用户头像
		String photo = rs.getString(5);
		
		//在session属性范围中保存用户名，积分，头像
		//session.setAttribute("username", username);
		//session.setAttribute("score", score);
		//session.setAttribute("photo", photo);
		
		//创建并填充实体bean
		User user = new User();
		user.setUserid(rs.getInt(1));
		user.setUsername(username);
		user.setScore(score);
		user.setPhoto(photo);
		
		//在session属性范围中保存用户对象
		session.setAttribute("user", user);
		
		//在application属性范围中更新并保存在线人数
		if(application.getAttribute("onlineCount")==null){  //第一位访客
			application.setAttribute("onlineCount", 1);
		}else{
			application.setAttribute("onlineCount", (Integer)application.getAttribute("onlineCount")+1);
		}
		
		//重定向到主页
		response.sendRedirect("index.jsp");
		
	}else{
		//out.println("<script>alert('用户名或密码错误，请重新输入');location.href = 'user_login.jsp'</script>");  //重新请求登录页面，之前的数据无法保留
		out.println("<script>alert('用户名或密码错误，请重新输入');history.back()</script>");   //回退到前一页，之前的数据可以保留
	}
	
	dbManager.closeConnection();
	
%>

</body>
</html>
<%@page import="java.net.URLEncoder"%>
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

	//转码处理
	request.setCharacterEncoding("utf-8");
	
	//接收数据
	String username = request.getParameter("username");

	//服务器请求转发
	//request.getRequestDispatcher("c.jsp?username=" + username).forward(request, response);
	//request.getRequestDispatcher("c.jsp").forward(request, response);

	//请求转发的跳页范围仅仅局限在项目内
	//request.getRequestDispatcher("www.baidu.com").forward(request, response);
	
	//客户端重定向
	//通过url重写方式传参，必须手工调用URLEncoder.encode()方法进行url编码
	response.sendRedirect("c.jsp?username=" + URLEncoder.encode(username, "utf-8"));
	
	//重定向可以跳转到项目外的url
	//response.sendRedirect("http://www.baidu.com");

%>
</body>
</html>
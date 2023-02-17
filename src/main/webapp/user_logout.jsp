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

	//删除session属性范围中的用户名，积分，头像
	//session.removeAttribute("username");
	//session.removeAttribute("score");
	//session.removeAttribute("photo");
	
	//删除session属性范围中的用户对象
	session.removeAttribute("user");
	
	//更新在线人数-1
	application.setAttribute("onlineCount", (Integer)application.getAttribute("onlineCount")-1);
	
	//重定向到登录页面
	response.sendRedirect("user_login.jsp");
	
%>
</body>
</html>
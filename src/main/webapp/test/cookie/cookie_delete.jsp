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
	Cookie cookie = new Cookie("username", "xxx");

	//0表示立即删除cookie
	cookie.setMaxAge(0);
	
	response.addCookie(cookie);
	
	out.print("cookie删除成功");
	
%>

	<a href="cookie_query.jsp">查看cookie</a>
</body>
</html>
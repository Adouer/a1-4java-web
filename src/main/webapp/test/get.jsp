<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 方式1：提供输入表单 -->
<form action="get_handle.jsp" method="get">
	用户名：<input type="text" name="username"/>
	<input type="submit" value="提交get请求">
</form>

<!-- 方式2：提供超链接 + url重写 -->
<a href="get_handle.jsp?username=yyy">提交get请求</a>

</body>
</html>
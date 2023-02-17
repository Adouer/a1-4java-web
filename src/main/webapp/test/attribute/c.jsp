<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>这是c.jsp</h3>
	pageContext&nbsp;&nbsp;范围=<%=pageContext.getAttribute("username")%> <br/>
	request范围=<%=request.getAttribute("username")%>   <br/>
	session范围=<%=session.getAttribute("username")%>   <br/>
	application范围=<%=application.getAttribute("username")%>   <br/>

</body>
</html>
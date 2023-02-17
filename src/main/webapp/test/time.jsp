<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<script type="text/javascript">
		
			var now = new Date();
			
			document.write("客户端的时间=" + (now.getYear()+1900) + "-" +
										(now.getMonth() + 1) + "-" +
										now.getDate() + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds() + "<br/>");
			
			
		</script>
	</head>

	<body>
	<%
	out.println("服务器的时间=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	%>
	
</body>

</html>
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
	String url = "http://localhost:8080/TestJSP/test/user_login.jsp?username=etc&password=123";
	out.println("URL = " + url + "<br/>");

	int position = url.indexOf(":");
	String protocol = url.substring(0, position);
	out.println("Protocol = " + protocol + "<br/>");
		
	int begin = url.indexOf("//") + 2;
	int end = url.indexOf(":", position + 1);
	String serverAddress = url.substring(begin, end);
	out.println("ServerAddress = " + serverAddress + "<br/>");
		
	begin = url.indexOf(":", position + 1) + 1;
	end = url.indexOf("/", end + 1);
	String serverPort = url.substring(begin, end);
	out.println("ServerPort = " + serverPort + "<br/>");
		
	begin = end + 1;
	end = url.indexOf("/", begin);
	String contextPath = url.substring(begin, end);
	out.println("ContextPath = " + contextPath + "<br/>");
		
	begin = url.lastIndexOf("/") + 1;
	end = url.lastIndexOf("?");
	String fileName = url.substring(begin, end);
	out.println("FileName = " + fileName + "<br/>");

	String queryString = url.substring(end + 1);
	out.println("QueryString = " + queryString + "<br/>");

	String[] entries = queryString.split("&");
	
	for(String entry : entries){
		
		String[] params = entry.split("=");
		
		out.println("参数名=" + params[0] + " 参数值=" + params[1] + "<br/>");
	}
	
%>
	 		

</body>
</html>
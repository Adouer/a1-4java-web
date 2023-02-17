<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

		
		<script>
		
			function showTime(){
				
				var now = new Date();
				
				document.write("客户端的时间=" + (now.getYear()+1900) + "-" +
											(now.getMonth() + 1) + "-" +
											now.getDate() + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds() + "<br/>");
						
			}
			
		</script>
</head>

<!-- setInterval()用于设置一段代码的执行间隔  第一个参数就是要执行的代码， 第二个参数是以毫秒为单位的时间间隔-->
<body onload="setInterval('showTime()', 1000)">

<%
	//out.println("服务器的时间=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	//response.addHeader("refresh", "1");
	//response.addHeader("refresh", "3;url=http://www.baidu.com");
%>
	
</body>
</html>
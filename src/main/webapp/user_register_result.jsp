<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>

	function change(){
	
		//通过id获取span标签  innerText属性表示标签内部的纯文本  innerHTML属性表示内部的HTML标记
		var time = document.getElementById("time");

		if(time.innerText > 1){
			time.innerText--;  	//改变span的内容
		}else{	
			location.href = "index.jsp";   //跳转到主页
		}
		
	}
	
</script>
</head>

<!-- setInterval()用于设置一段代码的执行间隔  第一个参数就是要执行的代码， 第二个参数是以毫秒为单位的时间间隔-->
<body onload="setInterval('change()', 1000)">

<h3 align="center"><font color="red" size="5">恭喜，新用户注册成功</font></h3>
<h4 align="center">系统将在<font color="red" size="5"><span id="time">3</span></font>秒后，自动跳转到主页，如果没有跳转，请点击<a href="index.jsp">这里</a></h4>

<%
    //延迟跳转 方式1---response对象添加响应头
	//response.addHeader("refresh", "333333;url=index.jsp");
%>

</body>

</html>
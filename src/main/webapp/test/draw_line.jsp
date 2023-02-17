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
	//接收数据
	String scount = request.getParameter("count");

	//默认值处理
	if(scount==null || "".equals(scount)){
		scount = "10";
	}
	
	//数据转型
	int count = 0;
	
	try{
		count = Integer.parseInt(scount);
	}catch(NumberFormatException e){
		out.println("数据输入错误，请<a href='draw_line_input.jsp'>重新输入</a>");
	}
	
%>

<form action="draw_line.jsp" method="post">
	画<input type="text" name="count" value="<%=count%>"/>条线
	<input type="submit" value="绘制">
</form>


<%
	for(int i=1;i<=count;i++){
		
		String color = "";
		
		if(i%2==0){
			color="red";	
		}else{
			color = "blue";
		}
		
		out.println("<hr color='" + color + "' width='" + i + "%'/>");
	}
%>


</body>
</html>
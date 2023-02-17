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
	//接收行数
	String srow = request.getParameter("row");

	//默认值处理
	if(srow==null || "".equals(srow)){
		srow = "10";
	}
	
	//数据转型
	int row = 0;
	
	try{
		row = Integer.parseInt(srow);
	}catch(NumberFormatException e){
		out.println("行数输入错误，请<a href='draw_table_input.jsp'>重新输入</a>");
	}
	
	//接收列数
	String scol = request.getParameter("col");

	//默认值处理
	if(scol==null || "".equals(scol)){
		scol = "10";
	}
	
	//数据转型
	int col = 0;
	
	try{
		col = Integer.parseInt(scol);
	}catch(NumberFormatException e){
		out.println("列数输入错误，请<a href='draw_table_input.jsp'>重新输入</a>");
	}
	
%>


<form action="draw_table.jsp" method="post">
	<input type="text" name="row" value="<%=row%>" />行
	<input type="text" name="col" value="<%=col%>" />列
	<input type="submit" value="绘制">
</form>



<table border="1">

<%
	int data = 1;
	
	for(int i=1; i<=row; i++){
	    
		String color = "";
		
		if(i%2==0){
			color = "#d0d0d0";
		}else{
			color = "#ffffff";		
		}
		
		out.print("<tr bgcolor=" + color + ">");
		
		for(int j=1; j<=col; j++){
			out.print("<td>" + (data++) + "</td>");
		}
		
		out.print("</tr>");
		
	}
 %>
</table>

</body>
</html>
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
	
	//开始年份
	String sbegin = request.getParameter("begin");
	
	//默认值处理
	if(sbegin==null || "".equals(sbegin)){
		sbegin = "2001";
	}
		
	//转型
	int begin = Integer.parseInt(sbegin);
	
	//结束年份
	String send = request.getParameter("end");
	
	//默认值处理
	if(send==null || "".equals(send)){
		send = "2100";
	}
		
	//转型
	int end = Integer.parseInt(send);
	
	//列数
	String scol = request.getParameter("col");
	
	//默认值处理
	if(scol==null || "".equals(scol)){
		scol = "10";
	}
		
	//转型
	int col = Integer.parseInt(scol);

%>


<form action="draw_year.jsp" method="post">
	从<input type="text" name="begin" value="<%=begin %>" />年 到<input type="text" name="end"  value="<%=end %>"/>年 <br/>
	每行<input type="text" name="col"  value="<%=col %>"/>个年份 <br/>
	<input type="submit" value="绘制">
</form>


<table border = "1">

<%
	//计算行数
	int row = (end-begin+1) % col==0 ? (end-begin+1) / col : (end-begin+1) / col + 1;

	int year = begin;
	
	for(int i=1;i<=row;i++){
		
		out.println("<tr>");
		
		for(int j=1;j<=col;j++){
		
			if(year<=end){
				if(isLeapYear(year)){
					out.println("<td><font color='red'><b>" + (year++) + "</b></font></td>");
				}else{
					out.println("<td>" + (year++) + "</td>");
				}
			}else{
				out.println("<td></td>");
			}
			
		}
		
		out.println("</tr>");
		
	}
%>
	
</table>

<%!
	boolean isLeapYear(int year){
	
		return (year%400==0 || year%4==0 && year%100!=0);
	}
%>	


</body>
</html>
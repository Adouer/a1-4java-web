<%@page import="com.adouer.a14javaweb.util.StringUtil"%>
<%@page import="com.adouer.a14javaweb.dbutil.DBManager"%>
<%@page import="java.sql.ResultSet"%>
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
	//字符编码
	request.setCharacterEncoding("utf-8");
	
	//接收用户名
	String username = request.getParameter("username");
	if(username==null){
		username = "";
	}
%>

<form action="user_query.jsp" method="post" onmou >

	用户名<input type="text" name="username" value="<%=username %>" />
	<input type="submit" value="查询" />
	
</form>


<table border="1">

<tr><td>用户头像</td><td>用户名</td><td>积分</td><td>性别</td><td>职业</td><td>注册时间</td></tr>

<%
	DBManager dbManager = DBManager.getInstance();

	String sql = "select * from user where username like ?";  

	ResultSet rs = dbManager.execQuery(sql, "%" + username + "%");  
	
	int row = 0;
	
	while(rs.next()){  	
			
%>		
	    <tr bgcolor='<%= row==0 ? "#d0d0d0" : "#ffffff" %>'>
	
		<td><img src="image/photo/<%=rs.getString(5)%>"/></td>

		<td><%=StringUtil.convertKeyword(rs.getString(2), username) %></td> 

		<td><%=rs.getInt(4) %></td>

		<td><%=rs.getString(6) %></td>
		
		<td><%=rs.getString(7) %></td>
		
		<td><%=StringUtil.convertDatetime(rs.getTimestamp(9)) %></td>
		
	    </tr>

<%			
		row = 1 - row;
	}
	
	dbManager.closeConnection();
			
%>

</table>

</body>
</html>
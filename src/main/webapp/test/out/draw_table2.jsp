<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">

<%
	int data = 1;
	
	for(int i=1; i<=10; i++){
	    
		String color = "";
		
		if(i%2==0){
			color = "#d0d0d0";
		}else{
			color = "#ffffff";		
		}
%>
		
		<tr bgcolor="<%=color %>">

<%		
		for(int j=1; j<=10; j++){
%>			
			<td><%=(data++)%></td>

<%		
}
%>
		
		</tr>
<%		
	}
 %>
</table>

</body>
</html>
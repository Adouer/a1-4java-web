<%@page import="java.util.List"%>
<%@page import="com.adouer.a14javaweb.entity.NewsType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

	function checkData(){
		
		var attachment = document.getElementById("attachment");
		var downloadscore = document.getElementById("downloadscore");
		
		if(attachment.value!=""){   //选择了附件
			
			if(downloadscore.value=="" || isNaN(downloadscore.value)){
				alert("附件扣除的积分必须是有效的数字");
				return false;
			}
		}
		
		return true;
	}
	
</script>
</head>
<body>

<c:if test="${sessionScope.user==null}">

<%

		//在sesson属性范围中保存登录后要返回的URL
		//session.setAttribute("prevURL", "news_add".jsp");
			
		//截取登录后要返回的URL
		String uri = request.getRequestURI();
 		String prevURL = uri.substring(uri.lastIndexOf("/") + 1);
 
		//在sesson属性范围中保存登录后要返回的URL
		session.setAttribute("prevURL", prevURL);
%>
	    <script>
	    	alert("您尚未登录，请先登录"); 		   //弹框
	    	location.href = "user_login.jsp";  //客户端跳转  href属性是默认属性
	    </script>
	    
</c:if>


<c:if test="${sessionScope.user!=null}">

<form action="NewsAddServlet" method="post" enctype="multipart/form-data" onsubmit="return checkData()">

	<h3 align="center">添加新闻</h3>
	<table cellpadding="1" cellspacing="0" align="center">
		
		<tr>
			<td>新闻类型</td>
			<td>
				<select name="typeid">
					<c:forEach var="newsType" items="${applicationScope.newsTypeList}">
						<option value="${newsType.typeid }">${newsType.typename}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>新闻标题</td>
			<td><input type="text" name="title"/></td>
		</tr>
		
		<tr>
			<td>新闻内容</td>
			<td><textarea rows="5" cols="20" name="content"></textarea></td>
		</tr>
		
		<tr>
			<td>新闻附件</td>
			<td><input type="file" name="attachment" id="attachment"/></td>
		</tr>
		
		<tr>
			<td>附件积分</td>
			<td><input type="text" name="downloadscore" id="downloadscore"/></td>
		</tr>	
		
		<tr>
			<td colspan="2" align="center"><input type="submit" value="添加新闻"/> </td>
		</tr>
		
		
	</table>

</form>

</c:if>

</body>
</html>
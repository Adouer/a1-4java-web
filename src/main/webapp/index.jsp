<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="com.adouer.a14javaweb.util.CookieUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${sessionScope.user==null}">

<%

		//获取名叫userInfo的cookie
		
		String userInfo = CookieUtil.findCookie(request, "userInfo");    //user1#1
		if(userInfo!=null){
			
			String[] userInfos = userInfo.split("#");
			
			String username = userInfos[0];
			String password = userInfos[1];
			
			response.sendRedirect("UserLoginServlet?username=" + username + "&password=" + password);
			
			return;
		}
			
%>
		
	    <script>
	    	alert("您尚未登录，请先登录"); 		   //弹框
	    	location.href = "user_login.jsp";  //客户端跳转  href属性是默认属性
	    </script>

</c:if>

<c:if test="${sessionScope.user!=null}">
		
	<img src="image/photo/${sessionScope.user.photo}" width="50" height="50"/>，欢迎【${sessionScope.user.username}】光临主页，
	   您的当前积分为【${sessionScope.user.score}】分，您是第【${applicationScope.onlineCount}】位访客
	
	<br/>
	
	<a href="user_query.jsp">用户查询</a>
	
	<br/>
	
	<a href="news_add.jsp">添加新闻</a>
	
	<br/>
	
	<a href="news_query.jsp">新闻列表查询</a>
	
	<br/>
	
	
	<a href="UserLogoutServlet">安全退出</a>

</c:if>


</body>
</html>
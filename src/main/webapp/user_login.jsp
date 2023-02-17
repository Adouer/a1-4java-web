<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.adouer.a14javaweb.util.CookieUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

<%
    String username = "";
    String password = "";

    //获取名叫userInfo的cookie

    String userInfo = CookieUtil.findCookie(request, "userInfo");    //user1#1
    if (userInfo != null) {

        String[] userInfos = userInfo.split("#");

        username = userInfos[0];
        password = userInfos[1];

    }
%>

<form action="UserLoginServlet" method="post">
    用户名<input type="text" name="username" value="<%=username%>"/><br/>
    密码<input type="password" name="password" value="<%=password%>"/><br/>
    <input type="submit" value="登录">
    <input type="button" value="新用户注册" onclick="location='user_register.jsp'"/>
    <br/>
    <input type="checkbox" name="member">记住用户名和密码
    <input type="checkbox" name="autoLogin">七天免登录

</form>

</body>
</html>
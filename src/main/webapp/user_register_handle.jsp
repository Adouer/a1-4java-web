<%@page import="java.util.Date"%>
<%@page import="com.adouer.a14javaweb.entity.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.adouer.a14javaweb.dbutil.DBManager"%>
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
	//乱码处理
	request.setCharacterEncoding("utf-8");
	
	//接收数据
	String username = request.getParameter("username");
	
	//服务器端数据校验---检查用户名的长度
	if(username.length()<5 ||username.length()>10){
		out.println("<script>alert('server:用户名长度必须在5-10个字符');history.back()</script>");
		return;
	}
	
	//服务器端数据校验---检查用户名是否存在
	DBManager dbManager = DBManager.getInstance();
	String sql = "select * from user where username = ?";
	ResultSet rs = dbManager.execQuery(sql, username);
	if(rs.next()){  //存在
		out.println("<script>alert('server:用户名已存在');history.back()</script>");
		dbManager.closeConnection();
		return;
	}
	dbManager.closeConnection();
	
	
	String password = request.getParameter("password");
	String photo = request.getParameter("photo");
	
	String gender = request.getParameter("gender");
	String job = request.getParameter("job");
	
	//getParameterValues()接收多值参数，返回String数组
	String[] interests = request.getParameterValues("interest");
	
	String interest = "";
	
	if(interests!=null){
		
		for(String s : interests){
			interest += s + " ";
		}
		
		interest.trim();
	}
	
	
	sql = "insert into user values(null, ?, ?, 10, ?, ?, ?, ?, ?)";
	
	if(dbManager.execUpdate(sql, username, password, photo, gender, job, interest, new Date())){  //插入成功
		
		sql = "select * from user where username = ? and password = ?";
		
		rs = dbManager.execQuery(sql, username, password);
		
		if(rs.next()){
			
			//创建并填充实体bean
			User user = new User();
			
			user.setUserid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setScore(rs.getInt(4));
			user.setPhoto(rs.getString(5));
			
			//关闭数据库连接
			dbManager.closeConnection();
			
			//在session属性范围中保存用户对象
			session.setAttribute("user", user);
			
			//在application属性范围中更新并保存在线人数
			if(application.getAttribute("onlineCount")==null){  //第一位访客
				application.setAttribute("onlineCount", 1);
			}else{
				application.setAttribute("onlineCount", (Integer)application.getAttribute("onlineCount")+1);
			}
			
			//重定向到注册结果的页面
			response.sendRedirect("user_register_result.jsp");
		}
	}else{
		out.println("<script>alert('注册失败，请重新检查数据是否输入正确');history.back()</script>");
	}
	

%>

</body>
</html>
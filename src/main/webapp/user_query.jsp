<%@page import="java.util.List"%>
<%@page import="com.adouer.a14javaweb.entity.User"%>
<%@page import="com.adouer.a14javaweb.util.StringUtil"%>
<%@page import="com.adouer.a14javaweb.dbutil.DBManager"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/myfunctions" prefix="myfn"%>  
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="js/user_query.js"></script>

<!-- 引入日历控件  -->
<script src="js/My97DatePicker/WdatePicker.js"></script>
  
</head>
<body>

<c:if test="${sessionScope.user==null}">

<%

		//在sesson属性范围中保存登录后要返回的URL
			
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

	<c:if test="${requestScope.list==null}">
	
	<%
		response.sendRedirect("UserQueryServlet");
	%>
	
	</c:if>
	
	<c:if test="${requestScope.list!=null}">
	
		<form action="UserQueryServlet" method="post" >
		
			用户名<input type="text" name="username" value="${username }"/>
			
			性别<select name="gender">
				    <option value="" ${ ""==gender ? "selected" : ""  } >全部</option>
					<option value="男"  ${ "男"==gender ? "selected" : "" } >男</option>
					<option value="女"  ${ "女"==gender ? "selected" : "" } >女</option>
			   </select>
			   
		   	职业<select name="job">
			    <option value="" ${  ""==job ? "selected" : "" } >全部</option>
				<option value="程序员" ${  "程序员"==job ? "selected" : "" } >程序员</option>
				<option value="美工" ${  "美工"==job ? "selected" : "" }>美工</option>
				<option value="项目经理" ${  "项目经理"==job ? "selected" : "" } >项目经理</option>
		   </select>
		   
		   <input type="submit" value="查询" />
		   
		   <br/>
		   
		        注册时间<select name="regtime" id="regtime" onchange="showTimeRange()">
				    <option value="全部时间" ${  "全部时间"==regtime ? "selected" : "" } >全部时间</option>
				    <option value="指定时间" ${  "指定时间"==regtime ? "selected" : "" } >指定时间</option>
			   </select>
			   
			   <!-- style属性用于设置css样式   属性名 :属性值   visibility: hidden/visible 或 display:none/block -->
		       <span id="timerange" style="visibility: ${'全部时间'==regtime ? 'hidden' : 'visible'} ">
		      		从<input type="text" name="begintime" value="${begintime}" readonly="readonly" class="Wdate" onfocus="WdatePicker()"/>
		      		到<input type="text" name="endtime" value="${endtime}" readonly="readonly" class="Wdate" onfocus="WdatePicker()"/>
		       </span>	
			
		</form>
		
		<form action="UserDeleteBatchServlet" method="post" onsubmit="return checkCount()">
		
		<table border="1" cellpadding="1" cellspacing="0">
		
		<tr><th>选择</th><th>用户头像</th><th>用户名</th><th>积分</th><th>性别</th><th>职业</th><th>注册时间</th><th>操作</th></tr>
		
		<c:set var="row" value="0"/>
		
		<c:forEach items="${list }" var="user">
		
			 <tr bgcolor='${row==0 ? "#d0d0d0" : "#ffffff"  }'>
		
				<td><input type="checkbox" name="userids" value="${user.userid }" onclick="checkAll()"></td>
		
				<td><img src="image/photo/${user.photo}"  width="50" height="50" /></td>
		
				<%-- <td><%=StringUtil.convertKeyword(user.getUsername(), username) %></td> 
		 --%>
		 		<td>${myfn:convertKeyword(user.username, username) }</td>
		 
				<td>${user.score }</td>
		
				<td>${user.gender }</td>
				
				<td>${user.job }</td>
				
				<%-- <td><%=StringUtil.convertDatetime(user.getRegtime()) %></td>
				 --%>
				 <td><fmt:formatDate value="${user.regtime }" pattern="yyyy-MM-dd HH:mm:ss"  dateStyle="medium"/></td>
				 
				<td><a href="UserDeleteServlet?userid${user.userid }" onclick="return confirm('是否确认删除用户【${user.username}】')">删除</a></td>
				
		    </tr>
		    
		    <c:set var="row" value="${1-row }"/>
		    
 		</c:forEach>
		
			<tr>
				<td>
					全选<input type="checkbox" id="all" onclick="selectAll()" />
					<br/>
					<input type="button" value="反选" onclick="selectReverse()" />
				</td>
				
				<td colspan="7" align="center">
					<input type="submit" value="批量删除"/>
				</td>
			</tr>
		
		</table>
		
		</form>

	</c:if>
	

</c:if>


</body>
</html>
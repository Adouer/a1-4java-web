<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="js/ckeditor/ckeditor.js"></script>

<script>

	//检查剩余的输入字数
	function checkWordCount(){
		
		var content = document.getElementById("content");
		var restWordCount = document.getElementById("restWordCount");
		
		if(content.value.length>20){
			content.value = content.value.substring(0, content.value.length-1);
		}
		
		restWordCount.innerText = 20 - content.value.length;
	}
	
	//记录当前点击的位置信息  1---已选中 0--没选中
	var stars = [0,0,0,0,0];
	
	//鼠标移入星星图形
	function enter(index){
	
		 for(var i=1; i<=5; i++){
			
			var star = document.getElementById("star" + i);
			if(i<=index){
				star.src ="image/star_yellow.gif";
			}else{
				star.src ="image/star_white.gif";
			}
		} 
	}
	
	//鼠标移出星星图形
	function leave(index){
		
		for(var i=1; i<=5; i++){
			
			var star = document.getElementById("star" + i);
			
			if(stars[i-1]==0){
				star.src ="image/star_white.gif";
			}
		}	
	}
	
	//鼠标点击星星图形
	function select(index){
		
		 for(var i=1; i<=5; i++){
				
			var star = document.getElementById("star" + i);
			
			if(i<=index){
				star.src ="image/star_yellow.gif";
				stars[i-1] = 1;  
			}else{
				star.src ="image/star_white.gif";
				stars[i-1] = 0;
			}
			
			//获取评分隐藏域
			var score = document.getElementById("score");
			
			//设置当前的评分值
			score.value = index;
			
		} 
	}
	
</script>
</head>
<body>

<c:if test="${requestScope.commentList!=null }">

	<table border="1" cellpadding="1" cellspacing="0">
	
	    <tr><td>发布者</td><td>评论内容</td><td>评论时间</td><td>评论打分</td></tr>
		
		<c:set var="row" value="0" />
		
		<c:forEach var="comment" items="${requestScope.commentList}">
		
			<tr bgcolor="${ row==0 ? '#d0d0d0' : '#ffffff' }">
		
				<td align="center">
					<img src="image/photo/${comment.user.photo}"/>
					<br/>
					${comment.user.username}
				</td>
				
				<td>${comment.content}</td>
				
			  	<td>${comment.pubtime}</td>
			    
			   <%--  <td>${comment.score}分</td>
				 --%>
				 <td>
				 	<c:forEach var="i" begin="1" end="${comment.score}">
				 		<img src="image/star_yellow.gif"/>
				 	</c:forEach>
				 	<c:forEach var="i" begin="1" end="${5 - comment.score}">
				 		<img src="image/star_white.gif"/>
				 	</c:forEach>
				 </td>
			</tr>
			
			<c:set var="row" value="${1 - row}" />
			
		</c:forEach>
		
	</table>
	
</c:if>
	
<c:if test="${sessionScope.user!=null }">
	
	<%-- 方式1：URL中附加当前新闻编号 --%>
	<%-- <form action="CommentAddServlet?newsid=${requestScope.newsid}" method="post"> class="ckeditor" --%>
	
	<form action="CommentAddServlet" method="post">

		评论内容<textarea name="content" rows="5" cols="30" id="content" onkeyup="checkWordCount()" ></textarea><br/>
		<br/>
		还可以输入<span id="restWordCount">20</span>字
		<br/>
		评论打分
		   	  <!-- <input type="radio" name="score" value="5" checked/>5分
			  <input type="radio" name="score" value="4"/>4分
			  <input type="radio" name="score" value="3"/>3分
			  <input type="radio" name="score" value="2"/>2分
			  <input type="radio" name="score" value="1"/>1分
 				-->
 			  <c:forEach var="i" begin="1" end="5">
				 <img src="image/star_white.gif" id="star${i}" 
				 		onmousemove="enter(${i})" 
				 		onmouseout="leave(${i})"
				 		onclick="select(${i})"/>
			  </c:forEach>
			  
			  <!-- 通过隐藏域传递评分值 -->
			  <input type="hidden" name="score" id="score" value="1"/>
			  
		<br/>

		<%-- 方式2：使用隐藏域传递当前新闻编号 --%>
		<input type="hidden" name="newsid" value="${requestScope.newsid}" />

		<input type="submit" value="发表评论" />	  
	</form>
</c:if>

<c:if test="${sessionScope.user==null }">

<%
	//保存上一次的URL
	session.setAttribute("prevURL", "CommentQueryServlet?newsid=" + request.getAttribute("newsid"));
%>
	
	发表评论前，请先<a href="user_login.jsp">登录</a>
</c:if>

</body>
</html>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="js/user_register.js"></script>

</head>
<body>

<h3 align="center"><font color="red" size="5">新用户注册</font></h3>

<form action="UserRegisterServlet" method="post" onsubmit="return checkData()" enctype="multipart/form-data">
	
	<table align="center">

		<tr>
			<td>用户名</td>
			<td width="500px">
				<input type="text" name="username" id="username" onblur="checkUsername()"/>
				<span id="usernameResult"></span>
			</td>
		</tr>
		
		<tr><td>密码</td><td><input type="password" name="password" id="password" onblur="checkPassword()"/></td></tr>
		
		<tr><td>确认密码</td><td><input type="password" id="password2" onblur="checkPassword2()"/><span id="password2Result"></span></td></tr>
		
		<tr>     	
		   <td>性别</td>
		   <td>
		   	<input type="radio" name="gender" value="男" checked />男
		   	<input type="radio" name="gender" value="女" />女
		   </td>
		 </tr>		 
		 
		 <tr>     	
		   <td>职业</td>
		   <td>
		   	  <select name="job">
		   		<option value="程序员" selected>程序员</option>
		   		<option value="美工">美工</option>
		   		<option value="项目经理">项目经理</option>
		   	  </select>
		   </td>
		 </tr>		
		  	 	
		 <tr>     	
		   <td>兴趣爱好</td>
		   <td>
		   		<input type="checkbox" name="interest" value="唱歌" />唱歌
		   		<input type="checkbox" name="interest" value="跳舞" />跳舞
		   		<input type="checkbox" name="interest" value="跑步" />跑步
		   		<input type="checkbox" name="interest" value="游泳" />游泳
		   </td>
		 </tr>	
		
		<tr>
			<td>头像</td>
			<td>
				<input type="radio" name="photo" value="1.gif"  onclick="changePhoto()" checked="checked"/><img src="image/photo/1.gif"/>
				<input type="radio" name="photo" value="2.gif"  onclick="changePhoto()" /><img src="image/photo/2.gif"/>
				<input type="radio" name="photo" value="3.gif"  onclick="changePhoto()" /><img src="image/photo/3.gif"/>
				<input type="radio" name="photo" value="4.gif"  onclick="changePhoto()" /><img src="image/photo/4.gif"/>
				<input type="radio" name="photo" value="5.gif"  onclick="changePhoto()" /><img src="image/photo/5.gif"/>
				<br/>
				<input type="radio" name="photo" id="photo" value="0.gif" onclick="changePhoto()"/>自定义头像
				<input type="file" name="file" id="file" style="visibility:hidden"/>
			</td>
		</tr>
		
		<tr>
			<td>验证码</td>
			<td>
				<input type="text" name="valCode" /> 
				<img src="ValCodeServlet" id="imgValCode" onclick="this.src=this.src+'?'"/>
				<input type="button" value="看不清，换一张" onclick="document.getElementById('imgValCode').src=document.getElementById('imgValCode').src+'?'" />
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="新用户注册">
				<input type="reset" value="重新填写">
			</td>
		</tr>
		
	</table>
	
</form>
	
</body>
</html>
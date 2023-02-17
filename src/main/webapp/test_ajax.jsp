<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

    var xhr = new XMLHttpRequest();

	function callAjaxServlet(){
		
		var username = document.getElementById("username");
		
		xhr.open("get", "TestAjaxServlet?username=" + encodeURI(username.value), true);
		
		xhr.send(null);
		
		xhr.onreadystatechange = callAjaxServletResult;
		
	}
	
	//回调函数
	function callAjaxServletResult(){
		
		var usernameResult = document.getElementById("usernameResult");
		
		//alert(xhr.readyState);
		if(xhr.readyState==4){
			//alert(xhr.status);
			
			if(xhr.status==200){
				usernameResult.innerText = xhr.responseText;
			}else{
				usernameResult.innerText = "调用失败";
			}
		}
	}
	
</script>
</head>
<body>

用户名<input type="text" id="username"/>
<input type="button" value="使用Ajax调用Servlet" onclick="callAjaxServlet()" />
<br/>
<span id="usernameResult"></span>
</body>
</html>
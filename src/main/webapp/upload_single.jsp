<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>

	function checkData(){
		
		var file = document.getElementById("file");
		
		if(file.value==""){
			alert("必须选择文件");
			return false;
		}
		
		return true;
	}
	
</script>
</head>
<body>

<form action="UploadSingleServlet" method="post" enctype="multipart/form-data" onsubmit="return true">
	<input type="text" name="username" /><br/>
	<input type="password" name="password" /><br/> 
	<input type="file" name="file" id="file"/><br/>
	<input type="submit" value="上传"/>
</form>
</body>
</html>
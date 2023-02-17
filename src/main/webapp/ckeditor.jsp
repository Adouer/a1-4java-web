<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/ckeditor/ckeditor.js"></script>
</head>
<body>

<form action="ckeditor_handle.jsp" method="post">
	
	<textarea name="content" rows="5" cols="10" class="ckeditor"></textarea>
	
	<input type="submit" />

</form>
</body>
</html>
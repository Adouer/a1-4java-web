<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

使用超链接下载文件<br/>
<a href="download/1.docx">下载word文件</a>
<a href="download/1.rar">下载rar文件</a>
<a href="download/1.jpg">下载jpg文件</a>
<a href="download/1.txt">下载txt文件</a>

<br/><br/>

使用SmartUpload下载文件<br/>
<a href="TestDownloadServlet?filename=1.docx">下载word文件</a>
<a href="TestDownloadServlet?filename=1.rar">下载rar文件</a>
<a href="TestDownloadServlet?filename=1.jpg">下载jpg文件</a>
<a href="TestDownloadServlet?filename=1.txt">下载txt文件</a>

</body>
</html>
package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.util.StringUtil;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "UploadMultiServlet", value = "/UploadMultiServlet")
public class UploadMultiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		//步骤1---创建上传组件---实例化SmartUpload对象
		SmartUpload smartUpload = new SmartUpload("utf-8");

		//步骤2---初始化上传组件---调用initialize()方法
		smartUpload.initialize(getServletConfig(), request, response);

		//步骤3---上传文件到服务器的临时内存中---调用upload()方法
		try {
			smartUpload.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}

		//步骤4---准备上传文件的存储路径和文件名---保证文件名唯一
		String serverFilePath = "e:/upload";

		//自动创建目录
		File dir = new File(serverFilePath);
		if(!dir.exists()){
			dir.mkdirs();
		}

		//步骤5---循环提取每一个上传文件，并手工检查文件大小和类型
		boolean flag = true;

		for(int i=0; i<smartUpload.getFiles().getCount(); i++){

			SmartFile smartFile = smartUpload.getFiles().getFile(i);

			if(smartFile.isMissing()){  //没上传文件
				continue;
			}

			int maxFileSize = 100;

			if(smartFile.getSize() > maxFileSize * 1024){
				out.println("<script>alert('第" + (i+1) + "个文件大小不能超过" + maxFileSize + "k');</script>");
				flag = false;
				continue;
			}

			String allowedFileList = "jpg,txt,xls，xlsx";

			if(!allowedFileList.contains(smartFile.getFileExt())){
				out.println("<script>alert('第" + (i+1) + "个文件类型必须是" + allowedFileList + "');</script>");
				flag = false;
				continue;
			}

			//生成新的文件名
			String serverFilename = StringUtil.convertFilename(smartFile.getFileName());   //原始文件名

			//步骤6---保存上传文件

			try {
				smartFile.saveAs(serverFilePath + "/" + serverFilename);
			} catch (SmartUploadException e) {
				e.printStackTrace();
				out.println("<script>alert('第" + (i+1) + "个文件上传失败');</script>");
			}
		}

		if(flag){
			out.println("<script>alert('全部文件上传成功');location='upload_multi.jsp'</script>");
		}else{
			out.println("<script>alert('部分文件上传成功');location='upload_multi.jsp'</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

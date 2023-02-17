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
import java.sql.SQLException;
@WebServlet(name = "UploadSingleServlet", value = "/UploadSingleServlet")
public class UploadSingleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		//获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		try {

			//步骤1---创建上传组件---实例化SmartUpload对象
			SmartUpload smartUpload = new SmartUpload("utf-8");

			//步骤2---初始化上传组件---调用initialize()方法
			smartUpload.initialize(getServletConfig(), request, response);

			//检查2---文件大小
			smartUpload.setMaxFileSize(100*1024);

			//检查3---文件类型
			//smartUpload.setAllowedFilesList("jpg,txt,xls");
			smartUpload.setDeniedFilesList("jpg,txt,xls");

			//步骤3---上传文件到服务器的临时内存中---调用upload()方法
			smartUpload.upload();

			//获取非文件表单域
			String username = smartUpload.getRequest().getParameter("username");
			System.out.println("username=" + username);

			//步骤4---提取上传文件
			SmartFile smartFile = smartUpload.getFiles().getFile(0);

			//检查1---文件非空
			if(smartFile.isMissing()){
				out.println("<script>alert('server:必须选择文件');history.back()</script>");
				return;
			}

			//步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
			String serverFilePath = "e:/upload";

			//自动创建目录
			File dir = new File(serverFilePath);
			if(!dir.exists()){
				dir.mkdirs();
			}

			//生成新的文件名
			String serverFilename = StringUtil.convertFilename(smartFile.getFileName());   //原始文件名

			//步骤6---保存上传文件
			smartFile.saveAs(serverFilePath + "/" + serverFilename);

			out.println("<script>alert('文件上传成功');location='upload_single.jsp'</script>");

		} catch (SmartUploadException e) {
			e.printStackTrace();
			out.println("<script>alert('文件上传失败');location='upload_single.jsp'</script>");
		} catch (SecurityException e) {
			e.printStackTrace();
			//out.println("<script>alert('文件大小不能超过100k,文件类型必须是jpg,txt,xls');location='upload_single.jsp'</script>");
			out.println("<script>alert('文件类型不能是jpg,txt,xls');location='upload_single.jsp'</script>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

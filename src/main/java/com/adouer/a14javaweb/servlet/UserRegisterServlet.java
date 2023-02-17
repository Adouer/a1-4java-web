package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.service.UserService;
import com.adouer.a14javaweb.service.impl.UserServiceImpl;
import com.adouer.a14javaweb.util.StringUtil;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "UserRegisterServlet", value = "/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");

        //获取out输出对象---需要输出内容时加此句
        PrintWriter out = response.getWriter();

        //获取session对象---需要session对象时加此句
        HttpSession session = request.getSession();

        //获取application对象
        ServletContext application = this.getServletContext();

	/*	//从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		//设置字符编码
		//request.setCharacterEncoding(charSet);
*/
        //****************文件上传开始********************************

        //创建上传组件---实例化SmartUpload对象
        SmartUpload smartUpload = new SmartUpload("utf-8");

        //初始化上传组件---调用initialize()方法
        smartUpload.initialize(getServletConfig(), request, response);


        String photo = null;

        //从配置文件中读取文件大小和允许的类型
        int maxFileSize = Integer.parseInt(this.getInitParameter("maxFileSize"));
        String allowedFileList = this.getInitParameter("allowedFileList");


        //上传文件到服务器的临时内存中---调用upload()方法
        try {

            //限定文件的大小
            smartUpload.setMaxFileSize(maxFileSize * 1024);
            smartUpload.setAllowedFilesList(allowedFileList);

            smartUpload.upload();

            //获取自定义头像的单选钮
            photo = smartUpload.getRequest().getParameter("photo");

            if ("0.gif".equals(photo)) {   //上传自定义头像

                //提取上传文件
                SmartFile smartFile = smartUpload.getFiles().getFile(0);

                //准备上传文件的存储路径和文件名---保证文件名唯一
                String serverFilePath = this.getServletContext().getRealPath("image/photo");

                //自动创建目录
                File dir = new File(serverFilePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                //生成新的文件名
                String serverFilename = StringUtil.convertFilename(smartFile.getFileName());

                //保存上传文件
                smartFile.saveAs(serverFilePath + "/" + serverFilename);

                //更新photo变量的值为新的文件名
                photo = serverFilename;
            }

        } catch (SmartUploadException e) {
            e.printStackTrace();
            out.println("<script>alert('文件上传失败');history.back()</script>");
            return;
        } catch (SecurityException e) {
            e.printStackTrace();
            out.println("<script>alert('文件大小不能超过" + maxFileSize + "k,文件类型必须是" + allowedFileList + "');history.back()</script>");
            return;
        }

        //****************文件上传结束********************************


        //接收验证码
        String valCode = smartUpload.getRequest().getParameter("valCode");

        //从session属性范围中取出正确的验证码
        String valCodeInSession = (String) session.getAttribute("valCodeInSession");

        //判断输入的验证码是否正确
        if (!valCode.equalsIgnoreCase(valCodeInSession)) {
            out.println("<script>alert('验证码不正确，请重新输入');history.back();</script>");
            return;
        }

        //接收数据
        String username = smartUpload.getRequest().getParameter("username");

        //服务器端数据校验---检查用户名的长度
        if (username.length() < 5 || username.length() > 10) {
            out.println("<script>alert('server:用户名长度必须在5-10个字符');history.back()</script>");
            return;
        }


        //组合业务层对象
        UserService useService = new UserServiceImpl();

        //调用业务方法---检查用户名是否存在
        if (!useService.checkUsername(username)) {   //用户名已存在
            out.println("<script>alert('server:用户名已存在');history.back()</script>");
            return;
        }

        String password = smartUpload.getRequest().getParameter("password");

        String gender = smartUpload.getRequest().getParameter("gender");
        String job = smartUpload.getRequest().getParameter("job");

        //getParameterValues()接收多值参数，返回String数组
        String[] interests = smartUpload.getRequest().getParameterValues("interest");

        String interest = "";

        if (interests != null) {

            for (String s : interests) {
                interest += s + " ";
            }

            interest.trim();
        }

        //从配置文件中读取注册的积分
        int registerScore = Integer.parseInt(this.getInitParameter("registerScore"));

        //创建并填充实体bean
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setScore(registerScore);
        user.setPhoto(photo);
        user.setGender(gender);
        user.setJob(job);
        user.setInterest(interest);
        user.setRegtime(new Date());

        //调用业务方法
        user = useService.register(user);

        if (user != null) {   //注册成功

            //在session属性范围中保存用户对象
            session.setAttribute("user", user);

            //在application属性范围中更新并保存在线人数
            if (application.getAttribute("onlineCount") == null) {  //第一位访客
                application.setAttribute("onlineCount", 1);
            } else {
                application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") + 1);
            }

            //重定向到注册结果的页面
            response.sendRedirect("user_register_result.jsp");

        } else {
            out.println("<script>alert('注册失败，请重新检查数据是否输入正确');history.back()</script>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
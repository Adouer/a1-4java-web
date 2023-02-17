package com.adouer.a14javaweb.servlet;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestAjaxServlet", value = "/TestAjaxServlet")
public class TestAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet()方法被调用。。。");
        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象---需要输出内容时加此句
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        if (username != null) {
            username = new String(username.getBytes("iso8859-1"), "utf-8");
            System.out.println("username=" + username);
        }
        String properties = System.getProperty("os.name");
        if ("admin".equals(username)) {
            out.print("用户名正确");
        } else {
            out.print("用户名失败");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost()方法被调用。。。");
        doGet(request, response);
    }
}

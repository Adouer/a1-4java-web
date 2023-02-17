package com.adouer.a14javaweb.servlet;

import com.adouer.a14javaweb.service.UserService;
import com.adouer.a14javaweb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckUsernameServlet", value = "/CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");

        //获取out输出对象---需要输出内容时加此句
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");

        username = new String(username.getBytes("iso8859-1"), "utf-8");

        UserService userService = new UserServiceImpl();

        if (userService.checkUsername(username)) {
            out.print("yes");
        } else {
            out.print("no");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
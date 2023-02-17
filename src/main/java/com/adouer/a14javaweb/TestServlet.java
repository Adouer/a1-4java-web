package com.adouer.a14javaweb;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试生命周期
 */
@WebServlet(name = "testServlet", value = "/testServlet", loadOnStartup = 1, initParams = {@WebInitParam(name = "param1", value = "1")})
public class TestServlet extends HttpServlet {

    /**
     * 加载
     */
    public TestServlet() {
        System.out.println("testServlet创建");
    }

    /**
     * 初始化
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init调用");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        System.out.println("doGet调用" + this);
        System.out.println("param1=" + this.getInitParameter("param1"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("doPost调用" + this);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("service调用" + this);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        System.out.println("service1调用" + this);

    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy调用" + this);
    }
}

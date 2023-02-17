package com.adouer.a14javaweb.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class AccessCountListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener, ServletRequestAttributeListener {
    int count;

    public AccessCountListener() {
        System.out.println("TestListener被创建。。。");
    }

    //生命周期---request
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request对象被创建。。。");
        ServletRequest request = sre.getServletRequest();
        count++;
    }

    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request对象被销毁创建。。。");
    }

    //生命周期---session
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session对象被创建。。。");
        //获取session对象
        HttpSession session = se.getSession();
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session对象被销毁。。。");
    }

    //生命周期---application
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象被创建。。。");
        //获取application对象
        ServletContext application = sce.getServletContext();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象被销毁。。。");
    }


    //属性变化---request
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("request属性范围中添加了属性" + srae.getName() + "=" + srae.getValue());
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("request属性范围中删除了属性" + srae.getName());
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("request属性范围中修改了属性" + srae.getName() + "=" + srae.getValue());
    }


    //属性变化---session
    public void attributeAdded(HttpSessionBindingEvent se) {
    }

    public void attributeRemoved(HttpSessionBindingEvent se) {
    }

    public void attributeReplaced(HttpSessionBindingEvent se) {
    }

    //属性变化---application
    public void attributeAdded(ServletContextAttributeEvent scab) {
    }

    public void attributeRemoved(ServletContextAttributeEvent scab) {
    }

    public void attributeReplaced(ServletContextAttributeEvent scab) {
    }
}
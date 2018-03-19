package com.codecool.web.listener;

import com.codecool.web.service.RegisterService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        RegisterService service = new RegisterService();
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("myDatabase", service);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}

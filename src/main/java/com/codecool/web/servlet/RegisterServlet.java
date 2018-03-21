package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.RegisterService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        RegisterService myData = (RegisterService)sc.getAttribute("myDatabase");
        User user1 = new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("role"));
        myData.checkRegisterFields(req, resp,user1);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registry.html").forward(req,resp);
    }

}

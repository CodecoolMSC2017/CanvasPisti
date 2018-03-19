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
        User unReg = myData.getUnReg();
        User registered = myData.getReg();
        User user1 = new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("role"));
        if (user1.getName() != "" && user1.getEmail() != "" && user1.getRole() != "") {
            req.setAttribute("register", registered);
            myData.getUserList().add(user1);
            req.getRequestDispatcher("registry.jsp").forward(req, resp);
        }else{
            req.setAttribute("register", unReg);
            req.getRequestDispatcher("registry.jsp").forward(req, resp);
        }

    }

}

package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userprofile")
public class CurrentUserServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*User user = LoginService.getCurrentUser();
        String userName = user.getName();
        String userEmail = user.getEmail();
        String userRole = user.getRole();

        req.setAttribute("username", userName);
        req.setAttribute("useremail", userEmail);
        req.setAttribute("userrole", userRole);
        req.getRequestDispatcher("userprofile.jsp").forward(req, resp);*/
    }
}

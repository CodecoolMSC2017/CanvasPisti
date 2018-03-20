package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = LoginService.getCurrentUser();
        String userName = user.getName();
        String userRole = user.getRole();

        if (req.getParameter("name").equals(userName) && req.getParameter("role") == null) {
            req.getRequestDispatcher("main.jsp").forward(req, resp);

        } else if (req.getParameter("role").equalsIgnoreCase(userRole)) {
            req.getRequestDispatcher("main.jsp").forward(req, resp);

        } else if (!req.getParameter("name").equals(userName) && req.getParameter("role") == null || req.getParameter("role").equalsIgnoreCase(userRole) && !req.getParameter("name").equals(userName)) {
            user.setName(req.getParameter("name"));
            req.setAttribute("name", user);
            req.getRequestDispatcher("main.jsp").forward(req, resp);

        } else if (!req.getParameter("role").equalsIgnoreCase(userRole) && req.getParameter("name").equals(userName)) {
            user.setRole(req.getParameter("role"));
            req.setAttribute("role", user);
            req.getRequestDispatcher("main.jsp").forward(req, resp);

        } else if (!req.getParameter("role").equals(userRole) && !req.getParameter("name").equals(userName)) {
            user.setRole(req.getParameter("role"));
            req.setAttribute("role", user);
            user.setName(req.getParameter("name"));
            req.setAttribute("name", user);
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }
    }
}

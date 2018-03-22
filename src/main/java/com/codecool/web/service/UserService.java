package com.codecool.web.service;

import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class UserService {

    public void changeUserAttr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User tempUser = (User) req.getSession().getAttribute("logged");
        String userName = tempUser.getName();
        String userRole = tempUser.getRole();
        if (req.getParameter("role") == null) {
            if (req.getParameter("name").equals(userName)) {
                req.getRequestDispatcher("main.jsp").forward(req, resp);

            } else if (!req.getParameter("name").equals(userName)) {
                tempUser.setName(req.getParameter("name"));
                req.setAttribute("name", tempUser);
                req.getRequestDispatcher("main.jsp").forward(req, resp);

            }
        } else if (req.getParameter("role").equalsIgnoreCase(userRole)) {
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        } else if (!req.getParameter("role").equalsIgnoreCase(userRole) && req.getParameter("name").equals(userName)) {

            tempUser.setRole(req.getParameter("role"));
            req.setAttribute("role", tempUser);
            req.getRequestDispatcher("main.jsp").forward(req, resp);

        } else if (!req.getParameter("role").equals(userRole) && !req.getParameter("name").equals(userName)) {
            tempUser.setRole(req.getParameter("role"));
            req.setAttribute("role", tempUser);
            tempUser.setName(req.getParameter("name"));
            req.setAttribute("name", tempUser);
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }
    }
}

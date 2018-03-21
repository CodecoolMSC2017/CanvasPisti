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
        User tempUser = (User)req.getSession().getAttribute("logged");
        String userName = tempUser.getName();
        String userEmail = tempUser.getEmail();
        String userRole = tempUser.getRole();

        req.setAttribute("username", userName);
        req.setAttribute("useremail", userEmail);
        req.setAttribute("userrole", userRole);
        req.getRequestDispatcher("userprofile.jsp").forward(req, resp);
    }
}

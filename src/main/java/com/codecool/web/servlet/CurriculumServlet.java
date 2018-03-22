package com.codecool.web.servlet;

import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/curriculum")
public class CurriculumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User tempUser = (User) req.getSession().getAttribute("logged");
        String userRole = tempUser.getRole();

        req.setAttribute("userrole", userRole);
        req.getRequestDispatcher("curriculum.jsp").forward(req, resp);
    }
}

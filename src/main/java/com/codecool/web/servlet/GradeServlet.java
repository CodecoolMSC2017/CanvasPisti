package com.codecool.web.servlet;


import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/grades")
public class GradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        User tempUser = (User)req.getSession().getAttribute("logged");
        ArrayList<AssignmentPage> pagez = db.getSubmissions().get(tempUser);
        String userName = tempUser.getName();
        req.setAttribute("username", userName);
        req.setAttribute("userassigns", pagez);
        req.getRequestDispatcher("grades.jsp").forward(req, resp);
    }
}

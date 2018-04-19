package com.codecool.web.servlet;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/scoring")
public class ScoringServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int actualScore = Integer.parseInt(req.getParameter("actualScore"));
        AssignmentPage page1 = (AssignmentPage) req.getSession().getAttribute("aPage");
        page1.setActualScore(actualScore);
        req.getRequestDispatcher("solutionGrade").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("pisti");
        try (Connection connection = getConnection(req.getServletContext())) {
            PageDao pageDao = new DatabasePageDao(connection);
            HashMap<User,ArrayList<AssignmentPage>> myMap = pageDao.getSubmissionList();
            for (Map.Entry<User, ArrayList<AssignmentPage>> entry : myMap.entrySet()) {
                if (entry.getKey().getEmail().equals(req.getParameter("student"))) {
                    req.setAttribute("student",entry.getKey());
                    req.getSession().setAttribute("student", entry.getKey());
                    ArrayList<AssignmentPage> pages = myMap.get(entry.getKey());
                    for (AssignmentPage asign : pages) {
                        if (asign.getTitle().equals(req.getParameter("item"))) {
                            req.setAttribute("aPage", asign);
                            req.getSession().setAttribute("aPage", asign);
                        }
                    }
                }
            }
            req.getRequestDispatcher("scoring.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
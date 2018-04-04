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
import java.util.Map;

@WebServlet("/scoring")
public class ScoringServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int actualScore = Integer.parseInt(req.getParameter("actualScore"));
        AssignmentPage page1 = (AssignmentPage) req.getSession().getAttribute("aPage");
        page1.setActualScore(actualScore);
        req.getRequestDispatcher("solutionGrade").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        for (Map.Entry<User, ArrayList<AssignmentPage>> entry : db.getSubmissions().entrySet()) {
            if (entry.getKey().getEmail().equals(req.getParameter("student"))) {
                req.setAttribute("student", entry.getKey());
                req.getSession().setAttribute("student",entry.getKey());
                ArrayList<AssignmentPage> pages = db.getSubmissions().get(entry.getKey());
                for (AssignmentPage asign : pages) {
                    if (asign.getTitle().equals(req.getParameter("item"))) {
                        req.setAttribute("aPage", asign);
                        req.getSession().setAttribute("aPage",asign);
                        req.getRequestDispatcher("scoring.jsp").forward(req, resp);
                    }
                }
            }
        }
    }
}
package com.codecool.web.servlet;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.ScoringService;

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
        ScoringService ss = new ScoringService();
        ss.Scoring(req, db);
        req.getRequestDispatcher("scoring.jsp").forward(req, resp);
    }
}
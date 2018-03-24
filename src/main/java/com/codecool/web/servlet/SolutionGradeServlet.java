package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solutionGrade")
public class SolutionGradeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        System.out.println(db.getSubmissions().size());
        req.setAttribute("allsubs", db.getSubmissions());
        req.getRequestDispatcher("solutionGrade.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

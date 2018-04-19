package com.codecool.web.servlet;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.model.Singletondb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/solutionGrade")
public class SolutionGradeServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())) {
            PageDao pageDao = new DatabasePageDao(connection);
            pageDao.getSubmissionList();
            System.out.println(pageDao.getSubmissionList().toString());
            req.setAttribute("allsubs", pageDao.getSubmissionList());

            req.getRequestDispatcher("solutionGrade.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      doGet(req,resp);

    }
}

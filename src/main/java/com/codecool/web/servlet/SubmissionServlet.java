package com.codecool.web.servlet;


import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.SubmissionService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/evaluate")
public class SubmissionServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            PageDao pageDao = new DatabasePageDao(connection);
            User tmpUser = (User)req.getSession().getAttribute("logged");
            User currentUser = userDao.findByEmail(tmpUser.getEmail());
            AssignmentPage page1 = (AssignmentPage)req.getSession().getAttribute("assign");
            AssignmentPage currentPage = (AssignmentPage) pageDao.findByAssignmentTitle(page1.getTitle());
            SubmissionService sm = new SubmissionService(userDao);
            sm.checkingSubmission(req,userDao,pageDao,currentPage,currentUser);
            resp.sendRedirect("curriculum");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

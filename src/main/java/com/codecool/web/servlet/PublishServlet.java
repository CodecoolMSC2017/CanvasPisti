package com.codecool.web.servlet;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.model.Page;
import com.codecool.web.model.Singletondb;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/publish")
public class PublishServlet extends AbstractServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Singletondb db = Singletondb.getInstance();
        try(Connection connection = getConnection(req.getServletContext())) {
            String[] arr = req.getParameterValues("name");
            PageDao pageDao = new DatabasePageDao(connection);
            ArrayList<Page>allpages = new ArrayList<>();
            allpages.addAll(pageDao.listAllText());
            allpages.addAll(pageDao.listAllAss());
            // UserService us = new UserService();
            // us.publishTasks(db, arr);
           // ArrayList<Page>tmpList =(ArrayList<Page>) req.getSession().getAttribute("alltxtasspage");
            pageDao.publishTask(allpages,arr);
            System.out.println(allpages.get(1).isPublished());
            resp.sendRedirect("curriculum");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

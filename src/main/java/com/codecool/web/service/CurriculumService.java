package com.codecool.web.service;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.model.*;
import com.codecool.web.service.simple.SimpleCurriculumService;
import com.codecool.web.service.simple.SimpleRegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public final class CurriculumService {

    public synchronized void createTextPage(HttpServletRequest req, HttpServletResponse resp,Connection connection) throws ServletException, IOException, SQLException {
        PageDao pageDao = new DatabasePageDao(connection);
        CurriculumServiceInt curriculumServiceInt =new SimpleCurriculumService(pageDao) ;
        if(req.getParameter("content") != "" && req.getParameter("title") != ""){
            Page textPage = new TextPage(req.getParameter("title"),false,req.getParameter("content"));
            textPage.setId(pageDao.getListSize());
            pageDao.addTextPage((TextPage)textPage);

            resp.sendRedirect("curriculum");
        }
        else if(req.getParameter("content") == "" && req.getParameter("title") == "" || req.getParameter("content") == "" && req.getParameter("title") == ""){
            resp.sendRedirect("curriculum");
        }
    }

    public synchronized void createAssignmentPage(HttpServletRequest req, HttpServletResponse resp,Connection connection) throws ServletException, IOException, SQLException {
        PageDao pageDao = new DatabasePageDao(connection);
        CurriculumServiceInt curriculumServiceInt =new SimpleCurriculumService(pageDao) ;
        Singletondb db = Singletondb.getInstance();
         Page assignmentPage = new AssignmentPage(req.getParameter("title"),false,req.getParameter("question"),"",Integer.parseInt(req.getParameter("maxScore")),0,0);
         assignmentPage.setId(pageDao.getListSize());
         pageDao.addAssignment((AssignmentPage)assignmentPage);
         resp.sendRedirect("curriculum");
    }
    public AssignmentPage getAssPage(){
        return null;
    }

}

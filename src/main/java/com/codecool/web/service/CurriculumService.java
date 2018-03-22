package com.codecool.web.service;

import com.codecool.web.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class CurriculumService {

    public void createTextPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        if(req.getParameter("content") != "" && req.getParameter("title") != ""){
            Page textPage = new TextPage(req.getParameter("title"),req.getParameter("content"));
            db.getPageList().add(textPage);
            resp.sendRedirect("curriculum");
        }
        else if(req.getParameter("content") == "" && req.getParameter("title") == "" || req.getParameter("content") == "" && req.getParameter("title") == ""){
            resp.sendRedirect("curriculum");
        }
    }

    public void createAssignmentPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
         Page assignmentPage = new AssignmentPage(req.getParameter("title"),req.getParameter("question"),"",Integer.parseInt(req.getParameter("maxScore")));
         db.getPageList().add(assignmentPage);
         resp.sendRedirect("curriculum");
    }
}

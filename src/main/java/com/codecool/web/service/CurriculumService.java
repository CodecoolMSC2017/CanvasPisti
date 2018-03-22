package com.codecool.web.service;

import com.codecool.web.model.Page;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.TextPage;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CurriculumService {

    public String getText(){return "You've created a textPage.";}

    public String emptyText(){return "You have to fill out all the fields!";}


    public void createTextPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        if(req.getParameter("content") != "" && req.getParameter("title") != ""){
            Page textPage = new TextPage(req.getParameter("title"),req.getParameter("content"));
            db.getPageList().add(textPage);
            req.setAttribute("msg", this.getText());
            req.getRequestDispatcher("curriculum.jsp").forward(req,resp);
        }
        else if(req.getParameter("content") == "" && req.getParameter("title") == "" || req.getParameter("content") == "" && req.getParameter("title") == ""){
            req.setAttribute("msg", this.emptyText());
            req.getRequestDispatcher("curriculum.jsp").forward(req,resp);
        }
    }
}

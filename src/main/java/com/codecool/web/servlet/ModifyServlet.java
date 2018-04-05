package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;
import com.codecool.web.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modify")
public class ModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        UserService us = new UserService();
        req.getSession().getAttribute("datepicker2");
        String[]arr = req.getParameterValues("name");
        us.modifyAttendance(req, db, arr);
        resp.sendRedirect("attendance");
    }

}

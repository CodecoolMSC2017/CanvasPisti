package com.codecool.web.servlet;


import com.codecool.web.model.Singletondb;
import com.codecool.web.service.SubmissionService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/evaluate")
public class SubmissionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        SubmissionService sm = new SubmissionService();
        sm.checkingSubmission(req,db);
        resp.sendRedirect("curriculum");

    }


}

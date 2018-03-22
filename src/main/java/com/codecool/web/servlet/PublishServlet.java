package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/publish")
public class PublishServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        String[] arr= req.getParameterValues("name");

        for (int i = 0; i <arr.length ; i++) {
            for (int j = 0; j <db.getPageList().size() ; j++) {

                if(arr[i].equals(db.getPageList().get(j).getTitle())){
                    db.getPageList().get(j).setPublished(true);
                }
            }
        }
        req.getRequestDispatcher("curriculum.jsp").forward(req,resp);
    }
}

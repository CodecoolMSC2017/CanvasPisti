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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        String[] arr= req.getParameterValues("name");

        if(arr != null) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < db.getPageList().size(); j++) {

                    if (arr[i].equals(db.getPageList().get(j).getTitle())) {
                        if (!db.getPageList().get(j).isPublished()) {
                            db.getPageList().get(j).setPublished(true);
                        } else {
                            db.getPageList().get(j).setPublished(false);
                        }
                    }
                }
            }
        }
        resp.sendRedirect("curriculum");
    }
}

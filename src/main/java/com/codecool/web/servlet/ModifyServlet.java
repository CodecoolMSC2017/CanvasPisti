package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/modify")
public class ModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        req.getSession().getAttribute("datepicker2");
       String[]arr = req.getParameterValues("name");
       if(arr!=null){
           for (Map.Entry<String, Map<User,String>> entry :db.getAttend().entrySet()) {
               if(entry.getKey().equals(req.getSession().getAttribute("datepicker2"))){
                   for (Map.Entry<User,String> entry2:db.getAttend().get(entry.getKey()).entrySet()) {
                       if(Arrays.asList(arr).contains(entry2.getKey().getEmail())){
                           if(entry2.getValue().equals("was here")){
                               entry2.setValue("wasnt here");
                           }else if(entry2.getValue().equals("wasnt here")){
                               entry2.setValue("was here");
                           }
                       }
                   }
               }
           }

       }
     resp.sendRedirect("attendance");
    }
}

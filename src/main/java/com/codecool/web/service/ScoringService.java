package com.codecool.web.service;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

public class ScoringService {

    public void Scoring(HttpServletRequest req, Singletondb db){
        for (Map.Entry<User, ArrayList<AssignmentPage>> entry : db.getSubmissions().entrySet()) {
            if (entry.getKey().getEmail().equals(req.getParameter("student"))) {
                req.setAttribute("student", entry.getKey());
                req.getSession().setAttribute("student", entry.getKey());
                ArrayList<AssignmentPage> pages = db.getSubmissions().get(entry.getKey());
                for (AssignmentPage asign : pages) {
                    if (asign.getTitle().equals(req.getParameter("item"))) {
                        req.setAttribute("aPage", asign);
                        req.getSession().setAttribute("aPage", asign);
                    }
                }
            }
        }
    }
}

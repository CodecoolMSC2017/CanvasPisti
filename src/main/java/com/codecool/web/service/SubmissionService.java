package com.codecool.web.service;


import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class SubmissionService {

    public void checkingSubmission(HttpServletRequest req, Singletondb db) throws IOException {
        int number = 0;
        User tempUser = (User)req.getSession().getAttribute("logged");
        AssignmentPage page1 = (AssignmentPage)req.getSession().getAttribute("assign");
        AssignmentPage newPage = new AssignmentPage(page1.getTitle(),page1.getQuestion(),page1.getAnswer(),page1.getMaxScore());
        newPage.setAnswer(req.getParameter("answerAssign"));
        if(db.getSubmissions().containsKey(tempUser)){
            ArrayList<AssignmentPage> pagez = db.getSubmissions().get(tempUser);
            for(AssignmentPage page : pagez) {
                if (page.getTitle().equals(newPage.getTitle())) {
                    number++;
                }
            }
            if(number == 0){
                pagez.add(newPage);
            }

        }else{
            ArrayList<AssignmentPage> asi = new ArrayList<>();
            asi.add(newPage);
            db.getSubmissions().put(tempUser,asi);
        }
    }

}

package com.codecool.web.service;


import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public final class SubmissionService {

    public void checkingSubmission(HttpServletRequest req, Singletondb db) throws IOException {
        User tempUser = (User)req.getSession().getAttribute("logged");
        AssignmentPage page=(AssignmentPage)req.getSession().getAttribute("assign");
        page.setAnswer(req.getParameter("answerAssign"));
        if(db.getSubmissions().containsKey(tempUser)){
            ArrayList<AssignmentPage> pagez = db.getSubmissions().get(tempUser);
            if(!pagez.contains(page)){
                pagez.add(page);

            }

        }else{
            ArrayList<AssignmentPage> asi = new ArrayList<>();
            asi.add(page);
            db.getSubmissions().put(tempUser,asi);
        }
    }

}

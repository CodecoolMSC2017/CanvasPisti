package com.codecool.web.service;


import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class SubmissionService {
    UserDao userDao;
        Map<User,ArrayList<AssignmentPage>> tmpMap = new HashMap<>();
    public SubmissionService(UserDao userDao){
        this.userDao = userDao;
    }

    public void checkingSubmission(HttpServletRequest req, UserDao userDao, PageDao pageDao,AssignmentPage page1,User tempUser) throws IOException, SQLException {
        int number = 0;

        AssignmentPage newPage = new AssignmentPage(page1.getTitle(),page1.isPublished(),page1.getQuestion(),page1.getAnswer(),page1.getMaxScore(),page1.getActualScore(),page1.getMinimumScore());
        newPage.setAnswer(req.getParameter("answerAssign"));
        if(tmpMap.containsKey(tempUser)){
            ArrayList<AssignmentPage> pagez = tmpMap.get(tempUser);
            for(AssignmentPage page : pagez) {
                if (page.getTitle().equals(newPage.getTitle())) {
                    number++;
                }
            }
            if(number == 0){
                pagez.add(newPage);
            }

        }else if (!tmpMap.containsKey(tempUser) ){
            ArrayList<AssignmentPage> asi = new ArrayList<>();
            asi.add(newPage);
            tmpMap.put(tempUser,asi);
            userDao.addSubmission(tempUser,newPage);
        }
    }

}
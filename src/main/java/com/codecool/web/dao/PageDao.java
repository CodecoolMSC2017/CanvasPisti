package com.codecool.web.dao;

import com.codecool.web.model.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PageDao {

    TextPage addTextPage(TextPage textPage) throws SQLException;

    AssignmentPage addAssignment(AssignmentPage assignmentPage) throws SQLException;

    Page add(Page page);

    List<Page> listAllText() throws SQLException;

    List<Page> listAllAss()throws SQLException;

    List<Page> listAllPages()throws SQLException;

    int getListSize()throws SQLException;

    void publishTask(ArrayList<Page> allpages,String[]arr) throws SQLException;

    void findAllAssignmentByUser(String email)throws SQLException;

    Page findByAssignmentTitle(String title) throws SQLException;

    HashMap<User,ArrayList<AssignmentPage>> getSubmissionList()throws SQLException;

    void updateAssignemnt(int score,String email, String title) throws SQLException;

    ArrayList<Grade> listAllGrades(String email) throws SQLException;
}


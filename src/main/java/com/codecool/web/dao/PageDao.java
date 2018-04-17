package com.codecool.web.dao;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Page;
import com.codecool.web.model.TextPage;

import java.sql.SQLException;
import java.util.List;

public interface PageDao {

    TextPage addTextPage(TextPage textPage) throws SQLException;

    AssignmentPage addAssignment(AssignmentPage assignmentPage) throws SQLException;

    Page add(Page page);

    List<Page> listAllText() throws SQLException;

    List<Page> listAllAss()throws SQLException;

    List<Page> listAllPages()throws SQLException;

    int getListSize()throws SQLException;
}
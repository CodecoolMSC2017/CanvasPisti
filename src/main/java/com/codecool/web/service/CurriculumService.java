package com.codecool.web.service;

import com.codecool.web.model.Page;

import java.util.ArrayList;
import java.util.List;

public final class CurriculumService {

    private List<Page> pageList = new ArrayList<>();

    public List<Page> getPageList() {
        return pageList;
    }
}

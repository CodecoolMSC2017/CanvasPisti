package com.codecool.web.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Singletondb {
    private Map<String,User>userMap= new HashMap<>();

    private List<Page> pageList;

    private volatile static Singletondb singletondbInstance;

    public List<Page> getPageList() {
        return pageList;
    }

    private Singletondb() {
        userMap = new HashMap<>();
        pageList = new ArrayList<>();
    }

    public static Singletondb getInstance() {
        if (singletondbInstance == null) {
            synchronized (Singletondb.class) {
                if (singletondbInstance == null) {
                    singletondbInstance = new Singletondb();
                }
            }
        }
        return singletondbInstance;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public User getUserFromMap(HashMap<String,User> tempMap,String key){
       return tempMap.get(key);
    }
}

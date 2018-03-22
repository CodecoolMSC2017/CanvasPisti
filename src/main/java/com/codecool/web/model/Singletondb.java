package com.codecool.web.model;

import java.util.HashMap;
import java.util.Map;

public class Singletondb {
    private Map<String,User>userMap= new HashMap<>();

    private volatile static Singletondb singletondbInstance;

    private Singletondb() {
        userMap = new HashMap<>();
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

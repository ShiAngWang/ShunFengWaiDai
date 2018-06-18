package com.example.sfwd.shunfengwaidai.manager;

/**
 * Created by lanjianzhong on 2018/4/23.
 */

import com.example.sfwd.shunfengwaidai.model.User;

public class UserManager {
    private User user;


    public UserManager() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static UserManager instance;
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }


}

package com.example.sfwd.shunfengwaidai.manager;

/**
 * Created by lanjianzhong on 2018/4/23.
 */

public class UserManager {
    private String loginUserName;

    public UserManager() {

    }
    private static UserManager instance;
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }
}

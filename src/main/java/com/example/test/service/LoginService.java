package com.example.test.service;

import com.example.test.bean.UserBean;

public interface LoginService {
    UserBean loginIn(String ID, String password);
    boolean changePassword(String ID,String oldPassword,String newPassword);
}

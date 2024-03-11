package com.taufik.services;

import com.taufik.entities.User;

public class UserService {
    public static boolean validation(String username, String password) {
        String[][] users = User.getUsers();
        User.setLoggedUsername(username);

        for (String[] user : users) {
            if (username.equals(user[0]) && password.equals(user[1])) {
                return true;
            }
        }
        return false;
    }
}

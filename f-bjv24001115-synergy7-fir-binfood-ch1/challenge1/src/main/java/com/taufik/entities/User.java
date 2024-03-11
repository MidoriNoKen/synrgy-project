package com.taufik.entities;

public class User {
    private static String[][] users = { {"admin", "admin123" }, {"taufik", "taufik321"} };
    private static String loggedUsername;

    public static String[][] getUsers() {
        return users;
    }

    public static void setLoggedUsername(String username) {
        loggedUsername = username;
    }

    public static String getLoggedUsername() {
        return loggedUsername;
    }
}

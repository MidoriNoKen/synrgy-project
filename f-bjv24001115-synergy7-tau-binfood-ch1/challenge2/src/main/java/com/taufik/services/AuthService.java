package com.taufik.services;

import com.taufik.models.entities.Staff;

public class AuthService {

  public static boolean validation(String username, String password) {
    for (Staff staff : Staff.getAll().values()) {
      if (
        staff.getUsername().equals(username) &&
        staff.getPassword().equals(password)
      ) {
        staff.setLoggedUsername(username);
        return true;
      }
    }
    return false;
  }

  public static String getLoggedUsername() {
    return new Staff().getLoggedUsername();
  }
}

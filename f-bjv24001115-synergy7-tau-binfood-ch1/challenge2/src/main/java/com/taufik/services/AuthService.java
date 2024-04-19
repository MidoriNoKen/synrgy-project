package com.taufik.services;

import com.taufik.models.entities.Staff;
import com.taufik.models.entities.User;
import java.util.Optional;

public class AuthService {

  public static Optional<Boolean> validation(String username, String password) {
    if (
      Staff
        .getAll()
        .values()
        .stream()
        .anyMatch(staff ->
          staff.getUsername().equals(username) &&
          staff.getPassword().equals(password)
        )
    ) {
      User.setLoggedUsername(username);
      return Optional.of(true);
    }
    User.setLoggedUsername(null);
    return Optional.of(false);
  }

  public static String getLoggedUsername() {
    return User.getLoggedUsername();
  }
}

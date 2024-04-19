package com.taufik.views;

import com.taufik.controllers.AuthController;
import com.taufik.utils.Util;
import java.util.Optional;

public class LoginView {

  private static Optional<Boolean> loginForm() {
    Util.header("Login BinarFud");
    String username = Util.userField("Masukkan username anda: ");
    String password = Util.userField("Masukkan password anda: ");
    System.out.println();
    return AuthController.authSection(username, password);
  }

  public static void loginPage() {
    do {
      try {
        Optional<Boolean> authResult = loginForm();
        if (authResult.isPresent() && authResult.get()) {
          DashboardView.DashboardPage();
        } else {
          Util.pausedLine("Username atau Password yang anda masukkan salah!");
        }
      } catch (Exception e) {
        Util.handleInputMismatch(e.getMessage());
      }
    } while (true);
  }
}

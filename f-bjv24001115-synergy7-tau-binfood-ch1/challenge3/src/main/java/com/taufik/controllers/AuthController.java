package com.taufik.controllers;

import com.taufik.services.AuthService;
import com.taufik.utils.Util;
import com.taufik.views.LoginView;
import java.util.Optional;

public class AuthController {

  public static Optional<Boolean> authSection(
    String username,
    String password
  ) {
    return AuthService.validation(username, password);
  }

  public static void logout() {
    Util.clearData();
    Util.userField(
      "Anda telah berhasil keluar dari aplikasi. Silakan login kembali.\n"
    );
    LoginView.loginPage();
  }
}

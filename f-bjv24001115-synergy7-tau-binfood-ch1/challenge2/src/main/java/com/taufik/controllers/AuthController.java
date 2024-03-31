package com.taufik.controllers;

import com.taufik.App;
import com.taufik.services.AuthService;
import com.taufik.utils.Util;
import com.taufik.views.LoginView;

public class AuthController {

  public static boolean authSection(String username, String password) {
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

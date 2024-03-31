package com.taufik.controllers;

import com.taufik.services.AuthService;

public class AuthController {

  public static boolean authSection(String username, String password) {
    return AuthService.validation(username, password);
  }
  //   public static void logout() {
  //     Util.clearData();
  //     Util.userField(
  //       "Anda telah berhasil keluar dari aplikasi. Silakan login kembali."
  //     );
  //     Main.main(new String[0]);
  //   }
}

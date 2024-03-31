package com.taufik;

import com.taufik.models.seeders.InitData;
import com.taufik.views.LoginView;

public class App {

  public static void main(String[] args) {
    InitData.initiateProduct();
    LoginView.loginPage();
  }
}

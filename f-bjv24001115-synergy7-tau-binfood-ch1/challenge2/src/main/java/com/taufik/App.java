package com.taufik;

import com.taufik.models.seeders.InitData;
import com.taufik.views.Dashboard;

public class App {

  public static void main(String[] args) {
    InitData.initiateProduct();
    Dashboard.loginPage();
  }
}

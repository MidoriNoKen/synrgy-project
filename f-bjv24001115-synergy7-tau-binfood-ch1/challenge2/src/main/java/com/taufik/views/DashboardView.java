package com.taufik.views;

import com.taufik.controllers.AuthController;
import com.taufik.controllers.CartController;
import com.taufik.controllers.ProductController;
import com.taufik.utils.Util;

public class DashboardView {

  private static void displayDashboard() {
    Util.header("Selamat Datang di Aplikasi BinarFud");
    ProductController.getProductList();
    Util.generalMenu("Keluar Aplikasi", "Pesan dan Bayar");
  }

  private static void handleUserOption(int userOption) {
    switch (userOption) {
      case 0:
        AuthController.logout();
      case 99:
        if (CartController.getUserCart() == null) {
          Util.handleInputMismatch("Keranjang masih kosong!");
        } else {
          CheckoutView.checkoutPage();
        }
      default:
        if (
          userOption > 0 && userOption <= ProductController.getProductSize()
        ) {
          QuantityView.ProductQuantityPage((long) userOption);
        }
    }
  }

  public static void DashboardPage() {
    do {
      try {
        displayDashboard();
        int userOption = Util.userOption();
        handleUserOption(userOption);
      } catch (Exception e) {
        Util.handleInputMismatch(e.getMessage());
      }
    } while (true);
  }
}

package com.taufik.views;

import com.taufik.controllers.AuthController;
import com.taufik.controllers.ProductController;
import com.taufik.models.entities.Product;
import com.taufik.utils.Util;

public class Dashboard {

  public static void loginPage() {
    do {
      Util.header("Login BinarFud");
      String username = Util.userField("Masukkan username anda: ");
      String password = Util.userField("Masukkan password anda: ");
      System.out.println();
      if (AuthController.authSection(username, password)) {
        DashboardPage();
      } else {
        Util.pausedLine("Username atau Password yang anda masukkan salah!");
      }
    } while (true);
  }

  public static void DashboardPage() {
    do {
      try {
        Util.header("Selamat Datang di Aplikasi BinarFud");
        ProductController.getProductList();
        Util.generalMenu("Keluar Aplikasi", "Pesan dan Bayar");

        int userOption = Util.userOption();
        switch (userOption) {
          case 0:
            break;
          case 99:
            break;
          default:
            if (
              userOption > 0 && userOption <= ProductController.getProductSize()
            ) {
              ProductQuantityPage((long) userOption);
            }
            break;
        }
      } catch (Exception e) {
        Util.handleInputMismatch(e.getMessage());
      }
    } while (true);
  }

  public static void ProductQuantityPage(Long productId) {
    do {
      try {
        Product productDetail = ProductController.getProductDetail(productId);
        Util.header("Masukkan Jumlah Pesanan");
        System.out.println(
          productDetail.getName() + " | " + productDetail.getPrice()
        );
        Util.generalMenu("Kembali ke Menu Utama", "");

        int userOption = Util.userOption();
        switch (userOption) {
          case 0:
            return;
          default:
            ProductController.postQuantity(productId, userOption);
            return;
        }
      } catch (Exception e) {
        Util.handleInputMismatch(e.getMessage());
      }
    } while (true);
  }
}

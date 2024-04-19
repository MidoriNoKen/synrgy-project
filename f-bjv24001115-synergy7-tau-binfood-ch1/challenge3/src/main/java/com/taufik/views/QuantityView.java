package com.taufik.views;

import com.taufik.controllers.ProductController;
import com.taufik.models.entities.Product;
import com.taufik.utils.Util;

public class QuantityView {

  private static void displayQuantity(Long productId) {
    Product productDetail = ProductController.getProductDetail(productId);
    Util.header("Masukkan Jumlah Pesanan");
    System.out.println(
      productDetail.getName() + " | " + productDetail.getPrice()
    );
    Util.generalMenu("Kembali ke Menu Utama", "");
  }

  private static void handleUserOption(Long productId, int userOption) {
    switch (userOption) {
      case 0:
        return;
      default:
        ProductController.postQuantity(productId, userOption);
    }
  }

  public static void ProductQuantityPage(Long productId) {
    do {
      try {
        displayQuantity(productId);
        int userOption = Util.userOption();
        handleUserOption(productId, userOption);
        return;
      } catch (Exception e) {
        Util.handleInputMismatch(e.getMessage());
      }
    } while (true);
  }
}

package com.taufik.views;

import com.taufik.controllers.CartController;
import com.taufik.utils.Util;
import java.util.InputMismatchException;

public class CheckoutView {

  private static void displayCheckout() {
    Util.header("Konfirmasi & Pembayaran");

    CartController.getProductsByUserCart();
    System.out.println(Util.additionSeparator());
    CartController.getTotalProductsByUserCart();

    Util.generalMenu("Kembali ke Menu Utama", "Konfirmasi dan Bayar");
  }

  private static void handleUserOption(int userOption) {
    switch (userOption) {
      case 0:
        return;
      case 99:
        PaymentView.paymentMethodPage();
    }
  }

  public static void checkoutPage() {
    try {
      displayCheckout();
      int userOption = Util.userOption();
      handleUserOption(userOption);
    } catch (InputMismatchException e) {
      Util.handleInputMismatch(e.getMessage());
    }
  }
}

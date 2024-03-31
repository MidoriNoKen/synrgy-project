package com.taufik.views;

import com.taufik.controllers.CartController;
import com.taufik.controllers.PaymentController;
import com.taufik.utils.Util;

public class PaymentView {

  public static void paymentPage(int paymentMethod, Long nominal) {
    Util.header("Konfirmasi Nominal Pembayaran");
    System.out.println("1. Ya\n2. Tidak\n");

    int userConfirmation = Util.userOption();
    switch (userConfirmation) {
      case 1:
        PaymentController.postPayment(paymentMethod, nominal);
        CashierView.receiptPage(paymentMethod, nominal);
        int option = Util.userOption();
        switch (option) {
          case 0:
            DashboardView.DashboardPage();
          case 99:
          // PaymentService.printReceipt(generateReceipt());
        }

        break;
      case 2:
        return;
    }
  }

  public static void paymentNominalPage(int paymentMethod) {
    do {
      try {
        Util.header("Masukkan Nominal Pembayaran");
        System.out.print(
          "Nominal yang perlu dibayarkan: " +
          CartController.getTotalPriceByUserCart() +
          " "
        );

        Long nominal = Util.userValue();

        if (nominal < CartController.getTotalPriceByUserCart()) {
          throw new Exception("Nominal yang dibayarkan kurang");
        }

        paymentPage(paymentMethod, nominal);
      } catch (Exception e) {
        Util.handleInputMismatch(e.getMessage());
      }
    } while (true);
  }

  public static void paymentMethodPage() {
    try {
      Util.header("Pilih Metode Pembayaran");
      PaymentController.getPaymentMethods();
      Util.generalMenu("Kembali ke Menu Utama", "");

      int userOption = Util.userOption();
      switch (userOption) {
        case 0:
          return;
        default:
          if (userOption < PaymentController.getPaymentMethodSize()) {
            paymentNominalPage(userOption);
          } else {
            throw new Exception();
          }
      }
    } catch (Exception e) {
      Util.handleInputMismatch(e.getMessage());
    }
  }
}

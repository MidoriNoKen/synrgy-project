package com.taufik.views;

import com.taufik.controllers.CartController;
import com.taufik.controllers.CashierController;
import com.taufik.utils.Util;

public class CashierView {

  public static void receiptPage(int paymentMethod, Long nominal) {
    Util.header("Konfirmasi Pembayaran");
    CartController.getProductsByUserCart();
    System.out.println(Util.additionSeparator());
    CartController.getTotalPriceByUserCart();

    System.out.println(
      "Nominal Pembayaran\t" + CashierController.getNominalPayment()
    );

    System.out.println(
      "Total Kembalian\t\t" + CashierController.changeReturn()
    );
    System.out.println(
      "Metode Pembayaran\t" + CashierController.getPaymentMethod()
    );
    Util.generalMenu("Kembali ke Menu Utama", "Cetak Struk Pembayaran");

    int option = Util.userOption();

    switch (option) {
      case 0:
        break;
      case 99:
        CashierController.postPrintReceipt();
    }
  }
}

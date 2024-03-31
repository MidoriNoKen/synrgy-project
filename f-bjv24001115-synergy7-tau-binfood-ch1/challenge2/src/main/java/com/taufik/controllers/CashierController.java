package com.taufik.controllers;

import com.taufik.models.entities.Cart;
import com.taufik.models.entities.Product;
import com.taufik.services.AuthService;
import com.taufik.services.CartService;
import com.taufik.services.PaymentService;
import com.taufik.services.ProductService;
import com.taufik.utils.Util;
import com.taufik.views.DashboardView;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CashierController {

  public static Long changeReturn() {
    return getNominalPayment() - CartService.getTotalPrice();
  }

  public static Long getNominalPayment() {
    return PaymentService.getNominalPayment();
  }

  public static String getPaymentMethod() {
    return PaymentService.getPaymentMethod();
  }

  public static String generateReceipt() {
    StringBuilder contentBuilder = new StringBuilder();

    for (Cart cart : CartService.getAll()) {
      Product product = ProductService.getById(cart.getProductId());
      contentBuilder
        .append(product.getName())
        .append("\t")
        .append(cart.getQuantity())
        .append("\t")
        .append(product.getPrice() * cart.getQuantity())
        .append("\n");
    }

    String content = contentBuilder.toString();
    String header = "====================\nBinarFood\n====================\n";
    String opening =
      "Terimakasih sudah memesan di BinarFood\n\nDi bawah ini adalah pesanan anda\n\n";
    String divider = Util.additionSeparator() + "\n";
    String total =
      "Total\t\t" +
      CartService.getTotalPrice() +
      "\t" +
      CartService.getTotalQuantity() +
      "\n\n";
    String paymentMethod =
      "Metode Pembayaran\t" + PaymentService.getPaymentMethod() + "\n";
    String payment =
      "Nominal Pembayaran\t" + PaymentService.getNominalPayment() + "\n";
    String changeReturn =
      "Total Kembalian\t\t" +
      (getNominalPayment() - CartService.getTotalPrice()) +
      "\n\n";
    String admin = "Nama Kasir\t\t" + AuthService.getLoggedUsername() + "\n\n";
    String footer =
      "====================\nSimpan struk ini sebagai bukti pembayaran\n====================\n";
    return (
      header +
      opening +
      content +
      divider +
      total +
      paymentMethod +
      payment +
      changeReturn +
      admin +
      footer
    );
  }

  public static void postPrintReceipt() {
    String fileName = "receipt.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      writer.write(generateReceipt());
      Util.pausedLine("Struk berhasil disimpan dengan nama " + fileName + "\n");
      Util.clearData();
      DashboardView.DashboardPage();
    } catch (IOException e) {
      System.err.println("Gagal menyimpan struk: " + e.getMessage());
    }
  }
}

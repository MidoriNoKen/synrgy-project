package com.taufik.controllers;

import com.taufik.models.entities.Cart;
import com.taufik.models.entities.Product;
import com.taufik.services.AuthService;
import com.taufik.services.CartService;
import com.taufik.services.PaymentService;
import com.taufik.services.ProductService;
import com.taufik.utils.Util;
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
      contentBuilder.append(
        String.format(
          "%-20s%10s%5s%n",
          product.getName(),
          product.getPrice() * cart.getQuantity(),
          cart.getQuantity()
        )
      );
    }

    String content = contentBuilder.toString();
    String header = "====================\nBinarFood\n====================\n";
    String opening =
      "Terimakasih sudah memesan di BinarFood\n\nDi bawah ini adalah pesanan anda\n\n";
    String divider = "------------------------------\n";
    String total = String.format(
      "%-20s%10s%5s%n%n",
      "Total",
      CartService.getTotalPrice(),
      CartService.getTotalQuantity()
    );
    String paymentMethod = String.format(
      "%-20s%-20s%n",
      "Metode Pembayaran",
      PaymentService.getPaymentMethod()
    );
    String payment = String.format(
      "%-20s%-20s%n",
      "Nominal Pembayaran",
      PaymentService.getNominalPayment()
    );
    String changeReturn = String.format(
      "%-20s%-20s%n%n",
      "Total Kembalian",
      (PaymentService.getNominalPayment() - CartService.getTotalPrice())
    );
    String admin = String.format(
      "%-20s%-20s%n%n",
      "Nama Kasir",
      AuthService.getLoggedUsername()
    );
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
    } catch (IOException e) {
      System.err.println("Gagal menyimpan struk: " + e.getMessage());
    }
  }
}

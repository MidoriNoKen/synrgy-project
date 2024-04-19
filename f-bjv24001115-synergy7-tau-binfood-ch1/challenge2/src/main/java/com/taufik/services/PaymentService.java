package com.taufik.services;

import com.taufik.models.entities.Payment;
import java.util.List;

public class PaymentService {

  public static Payment payment;

  public static List<String> getAllPaymentMethods() {
    return Payment.getAllPaymentMethods().isEmpty()
      ? null
      : Payment.getAllPaymentMethods();
  }

  public static void addPaymentMethod(String name) {
    if (name == null) throw new IllegalArgumentException(
      "Data yang dimasukkan tidak valid"
    );
    Payment.addPaymentMethod(name);
  }

  public static Payment addPayment(int paymentMethod, Long nominal) {
    if (paymentMethod == 0 || nominal == 0) throw new IllegalArgumentException(
      "Data yang dimasukkan tidak valid"
    );
    payment = new Payment(paymentMethod, nominal);
    return payment;
  }

  public static Long getNominalPayment() {
    return payment.getNominal();
  }

  public static String getPaymentMethod() {
    return payment.getPaymentMethodSelected();
  }

  public static void clear() {
    payment = null;
  }
}

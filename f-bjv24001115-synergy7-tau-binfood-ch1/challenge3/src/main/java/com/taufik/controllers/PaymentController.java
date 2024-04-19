package com.taufik.controllers;

import com.taufik.services.PaymentService;

public class PaymentController {

  public static void getPaymentMethods() {
    int order = 1;
    for (String paymentMethod : PaymentService.getAllPaymentMethods()) {
      System.out.println(order++ + ". " + paymentMethod);
    }
  }

  public static int getPaymentMethodSize() {
    return PaymentService.getAllPaymentMethods().size();
  }

  public static void postPayment(int paymentMethod, Long nominal) {
    PaymentService.addPayment(paymentMethod, nominal);
  }
}

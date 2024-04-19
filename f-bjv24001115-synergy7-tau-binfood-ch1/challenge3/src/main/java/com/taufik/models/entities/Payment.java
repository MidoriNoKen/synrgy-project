package com.taufik.models.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Payment {

  private String paymentMethodSelected;
  private Long nominal;
  private static List<String> paymentMethods = new ArrayList<>();

  public Payment(int paymentMethod, Long nominal) {
    this.paymentMethodSelected = paymentMethods.get(paymentMethod - 1);
    this.nominal = nominal;
  }

  public static void addPaymentMethod(String name) {
    paymentMethods.add(name);
  }

  public static List<String> getAllPaymentMethods() {
    return paymentMethods;
  }
}

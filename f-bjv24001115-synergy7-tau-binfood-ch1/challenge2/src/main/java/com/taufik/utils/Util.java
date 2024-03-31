package com.taufik.utils;

import java.util.Scanner;

public class Util {

  public static void header(String message) {
    System.out.println("====================");
    System.out.println(message);
    System.out.println("====================");

    System.out.println();
  }

  public static int userOption() {
    Scanner userInput = new Scanner(System.in);
    System.out.print("=> ");
    int value = userInput.nextInt();
    System.out.println();

    return value;
  }

  public static String userField(String message) {
    Scanner userInput = new Scanner(System.in);
    System.out.print(message);
    String value = userInput.nextLine();

    return value;
  }

  public static float userValue() {
    Scanner userInput = new Scanner(System.in);
    System.out.print("=> ");
    float value = userInput.nextFloat();
    System.out.println();

    return value;
  }

  public static void pausedLine(String message) {
    Scanner userInput = new Scanner(System.in);
    System.out.println();
    if (message != null) System.out.println(message);
    System.out.print("Tekan tombol apapun untuk melanjutkan...");
    userInput.nextLine();
    System.out.println();
  }

  public static String formatedFloat(Float value) {
    return String.format("%.0f", value);
  }

  public static String additionSeparator() {
    return "------------------------------+";
  }

  public static void generalMenu(String main, String additional) {
    System.out.println("\n0. " + main);
    if (!additional.isEmpty()) System.out.println("99. " + additional);
    System.out.println();
  }

  public static void handleInputMismatch(String message) {
    if (message == null) {
      Util.pausedLine("Input yang anda masukkan salah!");
    } else {
      Util.pausedLine(message);
    }
  }
  //   public static void clearData() {
  //     Cart.setCart(null);
  //     Payment.setNominalPayment(0);
  //     Payment.setPaymentMethod(null);
  //   }
}

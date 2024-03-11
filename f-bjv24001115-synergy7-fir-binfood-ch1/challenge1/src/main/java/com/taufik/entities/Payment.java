package com.taufik.entities;

public class Payment {
    private static String[] paymentMethods = { "Cash", "Bank", "Online Payment"};
    private static String paymentMethod;
    private static float nominalPayment;

    public static String[] getPaymentMethods() {
        return paymentMethods;
    }

    public static String getPaymentMethodByIndex(int i) {
        return paymentMethods[i];
    }

    public static void setPaymentMethod(String value) {
        paymentMethod = value;
    }

    public static String getPaymentMethod() {
        return paymentMethod;
    }

    public static void setNominalPayment(float value) {
        nominalPayment = value;
    }

    public static float getNominalPayment() {
        return nominalPayment;
    }
}

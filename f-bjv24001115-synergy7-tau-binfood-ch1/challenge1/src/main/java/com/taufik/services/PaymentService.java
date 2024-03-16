package com.taufik.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.taufik.entities.Cart;
import com.taufik.entities.Payment;
import com.taufik.entities.User;
import com.taufik.utils.Util;

public class PaymentService {
    public static boolean paymentMethodHandle(int userOption) {
        if (userOption > 0 && userOption <= Payment.getPaymentMethods().length) {
            Payment.setPaymentMethod(Payment.getPaymentMethodByIndex(userOption - 1));
            return true;
        }
        return false;
    }
    public static String changeReturn(float pay) {
        if (pay > CartService.total(2)) {
            return String.format("%.0f", pay - CartService.total(2));
        } else {
            return "0";
        }
    }

    public static void getPaymentMethodData() {
        for (int i = 0; i < Payment.getPaymentMethods().length; i++) {
            System.out.println(Util.order(i) + ". " + Payment.getPaymentMethodByIndex(i));
        }
    }

    public static void confirmationReceipt() {
        Util.header("Konfirmasi Pembayaran");
        CartService.getCartData();
        
        System.out.println(Util.additionSeparator());

        System.out.println("Total\t\t" + Util.formatedFloat(CartService.total(1)) + "\t" + Util.formatedFloat(CartService.total(2)) + "\n");
        System.out.println("Nominal Pembayaran\t" + Util.formatedFloat(Payment.getNominalPayment()));
        System.out.println("Total Kembalian\t\t" + PaymentService.changeReturn(Payment.getNominalPayment()));
        System.out.println("Metode Pembayaran\t" + Payment.getPaymentMethod());
        Util.generalMenu("Kembali ke Menu Utama", "Cetak Struk Pembayaran");
    }

    public static boolean confirmationReceiptHandler(int userConfirmation, float pay) {
        switch (userConfirmation) {
            case 1:
                Payment.setNominalPayment(pay);
                confirmationReceipt();
                int option = Util.userOption();
    
                switch (option) {
                    case 0:
                        break;
                    case 99:
                        PaymentService.printReceipt(generateReceipt());
                }
                break;
            case 2:
                break;
        }
        return false;
    }
    
    public static String generateReceipt() {
        String header = "====================\nBinarFood\n====================\n";
        String opening = "Terimakasih sudah memesan di BinarFood\n\nDi bawah ini adalah pesanan anda\n\n";
        String divider = Util.additionSeparator() + "\n";
        String total = "Total\t\t" + Util.formatedFloat(CartService.total(1)) + "\t" + Util.formatedFloat(CartService.total(2)) + "\n\n";
        String paymentMethod = "Metode Pembayaran\t" + Payment.getPaymentMethod() + "\n";
        String payment = "Nominal Pembayaran\t" + Util.formatedFloat(Payment.getNominalPayment()) + "\n";
        String changeReturn = "Total Kembalian\t\t" + PaymentService.changeReturn(Payment.getNominalPayment()) + "\n\n";
        String admin = "Nama Kasir\t\t" + User.getLoggedUsername() + "\n\n";
        String footer = "====================\nSimpan struk ini sebagai bukti pembayaran\n====================\n";
        return header + opening + Cart.getCartDataContent() + divider + total + paymentMethod + payment + changeReturn + admin + footer;
    }
    
    public static void printReceipt(String content) {
        String fileName = "receipt.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            Util.pausedLine("Struk berhasil disimpan dengan nama " + fileName + "\n");
            Util.clearData();
        } catch (IOException e) {
            System.err.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }
}

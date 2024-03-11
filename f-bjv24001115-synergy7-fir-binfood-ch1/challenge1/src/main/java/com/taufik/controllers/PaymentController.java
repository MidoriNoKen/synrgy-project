package com.taufik.controllers;

import com.taufik.services.CartService;
import com.taufik.services.PaymentService;
import com.taufik.utils.Util;

public class PaymentController {
    public static boolean paymentMethodSection() {
        while (true) {
            try {
                Util.header("Pilih Metode Pembayaran");
                PaymentService.getPaymentMethodData();
                Util.oneMenu("Kembali ke Menu Utama");
    
                int userOption = Util.userOption();;
                
                if (PaymentService.paymentMethodHandle(userOption)) {
                    return PaymentController.paymentSection();
                } else if (userOption == 0) {
                    return false;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                Util.handleInputMismatch(e.getMessage());
            }
        }
    }

    public static boolean paymentSection() {
        try {
            Util.header("Masukkan Nominal Pembayaran");
            System.out.print("Nominal yang perlu dibayarkan: " + Util.formatedFloat(CartService.total(2)) + " ");
            float pay = Util.userValue();

            if (pay < CartService.total(2)) {
                throw new Exception("Nominal yang dibayarkan kurang");
            }

            Util.header("Konfirmasi Nominal Pembayaran");
            System.out.println("1. Ya\n2. Tidak\n");

            int userConfirmation = Util.userOption();

            return PaymentService.confirmationReceiptHandler(userConfirmation, pay);
        } catch (Exception e) {
            Util.handleInputMismatch(e.getMessage());
        }
        return false;
    }
}

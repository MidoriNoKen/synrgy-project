package com.taufik.controllers;

import java.util.InputMismatchException;

import com.taufik.services.CartService;
import com.taufik.services.ProductService;
import com.taufik.utils.Util;

public class ProductController {
    public static boolean selectProductSection() {
        Util.header("Selamat Datang di Aplikasi Binar Fund");
        ProductService.productList();
        Util.generalMenu("Keluar Aplikasi", "Pesan dan Bayar");

        int userOption = Util.userOption();

        return ProductService.handleProductSelect(userOption);
    }

    public static boolean productQuantitySection(int userOption) {
        String productName = ProductService.getProductByIndex(0, userOption);
        String productPrice = ProductService.getProductByIndex(1, userOption);

        while (true) {
            try {
                Util.header("Masukkan Jumlah Pesanan");
                System.out.println(productName + " | " + productPrice);
                Util.oneMenu("Kembali ke Menu Utama");
            
                int userQuantity = Util.userOption();
                return CartService.addToCartHandle(userQuantity, productName, productPrice);
            } catch (Exception e) {
                Util.handleInputMismatch(e.getMessage());
            }
        }
    }

    public static void checkoutSection() {
        try {
            Util.header("Konfirmasi & Pembayaran");

            CartService.getCartData();
            System.out.println(Util.additionSeparator());
            System.out.println("Total\t\t" + Util.formatedFloat(CartService.total(1)) + "\t" + Util.formatedFloat(CartService.total(2)) + "\n");
            
            Util.generalMenu("Kembali ke Menu Utama", "Konfirmasi dan Bayar");
    
            int userOption = Util.userOption();

            ProductService.confirmationHandle(userOption);
        } catch (InputMismatchException e) {
            Util.handleInputMismatch(e.getMessage());
        }
    }
}

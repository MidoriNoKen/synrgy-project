package com.taufik.controllers;

import com.taufik.utils.Util;

public class MainController {
    public static boolean mainSection() {
        while (true) {
            try {
                Util.header("Selamat Datang di Aplikasi Binar Fund");
                ProductController.selectProductSection();
            } catch (Exception e) {
                Util.handleInputMismatch(e.getMessage());
            }
        }
    }
}

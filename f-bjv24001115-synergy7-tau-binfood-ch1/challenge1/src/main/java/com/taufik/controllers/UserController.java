package com.taufik.controllers;

import com.taufik.Main;
import com.taufik.services.UserService;
import com.taufik.utils.Util;

public class UserController {
    public static boolean authSection() {
        String username = Util.userField("Masukkan username anda: ");
        String password = Util.userField("Masukkan password anda: ");
        System.out.println();
        
        return UserService.validation(username, password);
    }

    public static void logout() {
        Util.clearData();
        Util.userField("Anda telah berhasil keluar dari aplikasi. Silakan login kembali.");
        Main.main(new String[0]);
    }
}

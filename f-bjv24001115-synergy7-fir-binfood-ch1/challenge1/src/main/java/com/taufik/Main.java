package com.taufik;

import com.taufik.controllers.ProductController;
import com.taufik.controllers.UserController;
import com.taufik.utils.Util;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n\n\n\n\n");
            Boolean status = UserController.authSection();
            
            if (status) {
                while (ProductController.selectProductSection());
            } else {
                Util.pausedLine("Username atau Password yang anda masukkan salah!");
            }
        }
    }
}

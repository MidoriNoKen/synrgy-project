package com.taufik.services;

import com.taufik.controllers.PaymentController;
import com.taufik.controllers.ProductController;
import com.taufik.controllers.UserController;
import com.taufik.entities.Cart;
import com.taufik.entities.Product;
import com.taufik.utils.Util;

public class ProductService {
    public static void productList() {
        String[][] products = Product.getProducts();
        for (int i = 0; i < products[0].length; i++) {
            System.out.println(Util.order(i) + ". " + products[0][i] + "\t| " + products[1][i]);
        }
    }

    public static boolean handleProductSelect(int userOption) {
        try {
            if (userOption >= 1 && userOption <= 5) {
                while (ProductController.productQuantitySection(userOption));
            } else if (userOption == 99) {
                if (Cart.getCart() == null) throw new Exception("Keranjang masih kosong!");
                ProductController.checkoutSection();
            } else if (userOption == 0) {
                UserController.logout();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Util.handleInputMismatch(e.getMessage());
        }
        return true;
    }

    public static void confirmationHandle(int userOption) {
        switch (userOption) {
            case 0:
                break;
            case 99:
                PaymentController.paymentMethodSection();
        }
    }
    
    public static String getProductByIndex(int row, int column) {
        return Product.getProducts()[row][column - 1];
    }
}

package com.taufik.services;

import com.taufik.entities.Cart;

public class CartService {
    public static boolean addToCartHandle(int userQuantity, String productName, String productPrice) {
        if (userQuantity != 0) Cart.saveCart(userQuantity, productName, productPrice);
        return false;
    }

    public static void getCartData() {
        for (int i = 0; i < Cart.getCart()[0].length; i++) {
            System.out.println(Cart.getCart()[0][i] + "\t" + Cart.getCart()[1][i] + "\t" + Cart.getCart()[2][i]);
        }
    }

    public static float total(int x) {
        String[][] cart = Cart.getCart();
        float total = 0;
        for (int i = 0; i < cart[0].length; i++) {
            total += Float.parseFloat(cart[x][i]);
        }
        return total;
    }
}

package com.taufik.entities;

public class Cart {
    private static String[][] cart = null;

    public static void setCart(String[][] value) {
        cart = value;
    }

    public static String[][] getCart() {
        return cart;
    }

    public static String getCartDataContent() {
        String content = "";
        for (int i = 0; i < cart[0].length; i++) {
            content += cart[0][i] + "\t" + cart[1][i] + "\t" + cart[2][i] + "\n";
        }
        return content;
    }

    public static void setNewCart() {
        if (cart == null) {
            cart = new String[3][1];
        } else {
            String[][] newCart = new String[3][cart[0].length + 1];
            for (int i = 0; i < cart[0].length; i++) {
                newCart[0][i] = cart[0][i];
                newCart[1][i] = cart[1][i];
                newCart[2][i] = cart[2][i];
            }
            cart = newCart;
        }
    }

    public static void saveCart( int quantity, String productName, String productPrice) {
        setNewCart();
        float totalPrice = Float.parseFloat(productPrice) * quantity;
        int lastIndex = cart[0].length - 1;
        cart[0][lastIndex] = productName;
        cart[1][lastIndex] = String.valueOf(quantity);
        cart[2][lastIndex] = String.format("%.0f", totalPrice);
    }
}

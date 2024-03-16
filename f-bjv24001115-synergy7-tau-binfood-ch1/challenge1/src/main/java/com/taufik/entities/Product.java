package com.taufik.entities;

public class Product {
    private static String[][] products = { {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"}, {"15000", "13000", "18000", "3000", "5000"} };
    
    public static String[][] getProducts() {
        return products;
    }
}

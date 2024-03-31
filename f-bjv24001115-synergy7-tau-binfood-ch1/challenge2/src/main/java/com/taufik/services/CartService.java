package com.taufik.services;

import com.taufik.models.entities.Cart;

public class CartService {

  public static void add(Long productId, int quantity) {
    if (productId == null || quantity == 0) {
      throw new IllegalArgumentException("Data yang dimasukkan tidak valid");
    }
    Cart newCart = new Cart(1L, productId, quantity);
    Cart.addProductToCart(1L, newCart);
  }
}

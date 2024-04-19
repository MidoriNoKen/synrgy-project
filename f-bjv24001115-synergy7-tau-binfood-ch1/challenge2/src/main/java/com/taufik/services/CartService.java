package com.taufik.services;

import com.taufik.models.entities.Cart;
import com.taufik.models.entities.Product;
import java.util.List;

public class CartService {

  public static List<Cart> getAll() {
    return Cart.getAll().isEmpty() ? null : Cart.getAll();
  }

  public static Long getTotalPrice() {
    Long totalPrice = 0L;
    for (Cart cart : CartService.getAll()) {
      totalPrice +=
        Product.getById(cart.getProductId()).getPrice() * cart.getQuantity();
    }
    return totalPrice;
  }

  public static Long getTotalQuantity() {
    Long totalQuantity = 0L;
    for (Cart cart : CartService.getAll()) {
      totalQuantity += cart.getQuantity();
    }
    return totalQuantity;
  }

  public static void add(Long productId, int quantity) {
    if (productId == null || quantity == 0) {
      throw new IllegalArgumentException("Data yang dimasukkan tidak valid");
    }
    Cart newCart = new Cart(productId, quantity);
    Cart.addProductToCart(newCart);
  }

  public static void clear() {
    Cart.getAll().clear();
  }
}

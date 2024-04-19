package com.taufik.services;

import com.taufik.models.entities.Cart;
import com.taufik.models.entities.Product;
import java.util.List;

public class CartService {

  public static List<Cart> getAll() {
    return Cart.getAll().isEmpty() ? null : Cart.getAll();
  }

  public static Long getTotalPrice() {
    return CartService
      .getAll()
      .stream()
      .mapToLong(cart ->
        Product.getById(cart.getProductId()).getPrice() * cart.getQuantity()
      )
      .sum();
  }

  public static Long getTotalQuantity() {
    return CartService.getAll().stream().mapToLong(Cart::getQuantity).sum();
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

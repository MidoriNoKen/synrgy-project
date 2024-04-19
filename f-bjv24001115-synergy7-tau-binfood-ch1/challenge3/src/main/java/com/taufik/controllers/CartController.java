package com.taufik.controllers;

import com.taufik.models.entities.Cart;
import com.taufik.models.entities.Product;
import com.taufik.services.CartService;
import com.taufik.services.ProductService;
import java.util.List;

public class CartController {

  public static List<Cart> getUserCart() {
    return CartService.getAll();
  }

  public static void getProductsByUserCart() {
    for (Cart cart : CartService.getAll()) {
      Product product = ProductService.getById(cart.getProductId());
      System.out.println(
        product.getName() +
        "\t" +
        cart.getQuantity() +
        "\t" +
        product.getPrice() *
        cart.getQuantity()
      );
    }
  }

  public static Long getTotalPriceByUserCart() {
    return CartService.getTotalPrice();
  }

  public static void getTotalProductsByUserCart() {
    System.out.println(
      "Total\t\t" +
      CartService.getTotalQuantity() +
      "\t" +
      getTotalPriceByUserCart() +
      "\n"
    );
  }
}

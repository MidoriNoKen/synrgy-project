package com.taufik.models.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Cart {

  private Long id;
  private Long productId;
  private int quantity;
  private static List<Cart> carts = new ArrayList<>();

  public Cart(Long productId, int quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public static void addProductToCart(Cart cart) {
    carts.add(cart);
  }

  public static List<Cart> getAll() {
    return carts;
  }
}

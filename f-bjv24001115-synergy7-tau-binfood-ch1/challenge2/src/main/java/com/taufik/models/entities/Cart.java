package com.taufik.models.entities;

import java.util.HashMap;
import java.util.Map;
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
  private static Map<Long, Cart> carts = new HashMap<>();

  public Cart(Long id, Long productId, int quantity) {
    this.id = id;
    this.productId = productId;
    this.quantity = quantity;
  }

  public static void addProductToCart(Long id, Cart cart) {
    carts.put(cart.getId(), cart);
  }
}

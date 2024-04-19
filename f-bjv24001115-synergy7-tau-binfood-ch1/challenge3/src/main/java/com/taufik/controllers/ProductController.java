package com.taufik.controllers;

import com.taufik.models.entities.Product;
import com.taufik.services.CartService;
import com.taufik.services.ProductService;

public class ProductController {

  public static void getProductList() {
    int i = 1;
    for (Product product : ProductService.getAll().values()) {
      System.out.println(
        i++ + ". " + product.getName() + "\t| " + product.getPrice()
      );
    }
  }

  public static int getProductSize() {
    return ProductService.getAll().size();
  }

  public static Product getProductDetail(Long id) {
    return ProductService.getById(id);
  }

  public static void postQuantity(Long productId, int userOption) {
    CartService.add(productId, userOption);
  }
}

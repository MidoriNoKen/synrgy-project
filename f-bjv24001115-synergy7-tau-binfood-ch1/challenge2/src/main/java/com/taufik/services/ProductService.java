package com.taufik.services;

import com.taufik.models.entities.Product;
import java.time.LocalDateTime;

public class ProductService {

  public static Product create(Long id, String name, Long price) {
    if (id == null || name == null || price == null) {
      throw new IllegalArgumentException("All parameters must not be null");
    }

    return new Product(id, name)
      .setPrice(price)
      .setCreatedDate(LocalDateTime.now())
      .setUpdatedDate(LocalDateTime.now());
  }
}

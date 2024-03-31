package com.taufik.services;

import com.taufik.models.entities.Product;
import java.time.LocalDateTime;
import java.util.Map;

public class ProductService {

  public static Map<Long, Product> getAll() {
    return Product.getAll().isEmpty() ? null : Product.getAll();
  }

  public static Product getById(Long id) {
    return Product.getById(id) == null ? null : Product.getById(id);
  }

  public static void add(Long id, String name, Long price) {
    if (id == null || name == null || price == null) {
      throw new IllegalArgumentException("Data yang dimasukkan tidak valid");
    }

    if (Product.getById(id) != null) throw new IllegalArgumentException(
      "ID yang dimasukkan sudah ada"
    );

    Product newProduct = new Product(id, name)
      .setPrice(price)
      .setCreatedDate(LocalDateTime.now())
      .setUpdatedDate(LocalDateTime.now());

    Product.addProduct(newProduct);
  }
}

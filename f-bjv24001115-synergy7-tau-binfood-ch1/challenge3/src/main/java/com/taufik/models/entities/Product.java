package com.taufik.models.entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Product {

  private Long id;
  private String name;
  private long price;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private static Map<Long, Product> products = new HashMap<>();

  public Product(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public static void addProduct(Product product) {
    products.put(product.getId(), product);
  }

  public static Map<Long, Product> getAll() {
    return products;
  }

  public static Product getById(Long id) {
    return products.get(id);
  }
}

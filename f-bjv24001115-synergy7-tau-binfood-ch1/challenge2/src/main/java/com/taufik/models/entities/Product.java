package com.taufik.models.entities;

import java.time.LocalDateTime;
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

  public Product(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}

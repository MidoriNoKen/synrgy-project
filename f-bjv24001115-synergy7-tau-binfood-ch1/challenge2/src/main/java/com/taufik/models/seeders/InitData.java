package com.taufik.models.seeders;

import com.taufik.models.entities.Product;
import com.taufik.models.enums.Gender;
import com.taufik.services.ProductService;
import com.taufik.services.StaffService;
import java.time.LocalDate;
import java.util.*;

public class InitData {

  public static Map<Long, Product> productList = new LinkedHashMap<>();

  public static void initiateProduct() {
    StaffService.create(
      1L,
      "midorinoken",
      "Midori313131",
      "midor1nok3n@gmail.com",
      Gender.MALE,
      LocalDate.of(2001, 1, 24),
      "Pasuruan"
    );

    StaffService.create(
      2L,
      "diahihi",
      "Diah123123",
      "diah@gmail.com",
      Gender.FEMALE,
      LocalDate.of(2005, 8, 9),
      "Malang"
    );

    ProductService.create(1L, "Nasi Goreng", 15000L);
    ProductService.create(2L, "Mi Goreng", 13000L);
    ProductService.create(3L, "Nasi + Ayam", 18000L);
    ProductService.create(4L, "Es Teh Manis", 3000L);
    ProductService.create(5L, "Es Jeruk", 5000L);
  }
}

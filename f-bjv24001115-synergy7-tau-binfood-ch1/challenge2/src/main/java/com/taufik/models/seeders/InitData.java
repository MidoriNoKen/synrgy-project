package com.taufik.models.seeders;

import com.taufik.models.enums.Gender;
import com.taufik.services.ProductService;
import com.taufik.services.StaffService;
import java.time.LocalDate;

public class InitData {

  public static void initiateProduct() {
    StaffService.add(
      1L,
      "midorinoken",
      "Midori313131",
      "midor1nok3n@gmail.com",
      "Taufik Ardiansyah Putra",
      Gender.MALE,
      LocalDate.of(2001, 1, 24),
      "Pasuruan"
    );

    StaffService.add(
      2L,
      "diahihi",
      "Diah123123",
      "diah@gmail.com",
      "Diah Ayu Rahma",
      Gender.FEMALE,
      LocalDate.of(2005, 8, 9),
      "Malang"
    );

    StaffService.add(
      3L,
      "admin",
      "admin",
      "admin@gmail.com",
      "Admin",
      Gender.MALE,
      LocalDate.of(2001, 1, 1),
      "Admin"
    );

    ProductService.add(1L, "Nasi Goreng", 15000L);
    ProductService.add(2L, "Mi Goreng", 13000L);
    ProductService.add(3L, "Nasi + Ayam", 18000L);
    ProductService.add(4L, "Es Teh Manis", 3000L);
    ProductService.add(5L, "Es Jeruk", 5000L);
  }
}

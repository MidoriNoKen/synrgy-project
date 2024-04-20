package com.taufik.services;

import com.taufik.models.entities.Staff;
import com.taufik.models.enums.Gender;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Optional;

public class StaffService {

  public static Map<Long, Staff> getAll() {
    return Optional
      .ofNullable(Staff.getAll())
      .orElseThrow(() -> new IllegalStateException("Data tidak tersedia"));
  }

  public static Staff getById(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID tidak valid");
    }

    Staff staff = Staff.getById(id);
    if (staff == null) {
      throw new IllegalArgumentException("ID tidak valid");
    }

    return staff;
  }

  public static void add(
    Long id,
    String username,
    String password,
    String email,
    String fullname,
    Gender gender,
    LocalDate bornDate,
    String address
  ) {
    if (id == null) {
      throw new IllegalArgumentException("ID tidak valid");
    }

    if (
      username == null ||
      password == null ||
      email == null ||
      fullname == null ||
      gender == null ||
      bornDate == null ||
      address == null ||
      bornDate.isAfter(LocalDate.now()) // Menambahkan pengecekan bornDate
    ) {
      throw new IllegalArgumentException("Data yang dimasukkan tidak valid");
    }

    if (Staff.getById(id) != null) {
      throw new IllegalArgumentException("ID yang dimasukkan sudah ada");
    }

    int age = Period.between(bornDate, LocalDate.now()).getYears();

    Staff newStaff = new Staff(id, username, password, email)
      .setFullname(fullname)
      .setBornDate(bornDate)
      .setAge(age)
      .setAddress(address)
      .setGender(gender);

    Staff.addStaff(newStaff);
  }
}

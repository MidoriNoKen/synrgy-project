package com.taufik.services;

import com.taufik.models.entities.Staff;
import com.taufik.models.enums.Gender;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

public class StaffService {

  public static Map<Long, Staff> getAll() {
    return Staff.getAll().isEmpty() ? null : Staff.getAll();
  }

  public static Staff getById(Long id) {
    return Staff.getById(id) == null ? null : Staff.getById(id);
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
    if (
      id == null ||
      username == null ||
      password == null ||
      email == null ||
      fullname == null ||
      gender == null ||
      bornDate == null ||
      address == null
    ) {
      throw new IllegalArgumentException("Data yang dimasukkan tidak valid");
    }

    if (Staff.getById(id) != null) throw new IllegalArgumentException(
      "ID yang dimasukkan sudah ada"
    );

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

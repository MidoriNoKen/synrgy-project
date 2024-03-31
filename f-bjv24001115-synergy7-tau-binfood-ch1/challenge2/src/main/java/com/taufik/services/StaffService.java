package com.taufik.services;

import com.taufik.models.entities.Staff;
import com.taufik.models.enums.Gender;
import java.time.LocalDate;
import java.time.Period;

public class StaffService {

  public static Staff create(
    Long id,
    String username,
    String password,
    String email,
    Gender gender,
    LocalDate bornDate,
    String address
  ) {
    if (
      id == null ||
      bornDate == null ||
      address == null ||
      username == null ||
      password == null ||
      email == null ||
      gender == null
    ) {
      throw new IllegalArgumentException("All parameters must not be null");
    }

    int age = Period.between(bornDate, LocalDate.now()).getYears();

    return new Staff(id, username, password, email)
      .setBornDate(bornDate)
      .setAge(age)
      .setAddress(address)
      .setGender(gender);
  }
}

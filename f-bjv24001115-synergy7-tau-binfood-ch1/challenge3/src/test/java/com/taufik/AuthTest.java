package com.taufik;

import static org.junit.jupiter.api.Assertions.*;

import com.taufik.models.entities.Staff;
import com.taufik.models.enums.Gender;
import com.taufik.services.AuthService;
import com.taufik.services.StaffService;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthTest {

  /*
        1. Login dengan akun yang sesuai
        2. Login dengan username dan atau password yang salah
        3. Cek LoggedUsername sebelum Login
        4. Cek LoggedUsername setelah Login
        5. Cek LoggedUsername dengan username dan atau password salah
    */

  private String username = "test", password = "test", email =
    "test@mail.com", fullname = "test", address = "test";
  private LocalDate bornDate = LocalDate.of(2001, 1, 1);
  private Gender gender = Gender.MALE;

  @BeforeEach
  void setUp() {
    Optional<Map<Long, Staff>> allStaffs = Optional.ofNullable(
      StaffService.getAll()
    );
    allStaffs.ifPresent(Map::clear);

    StaffService.add(
      1L,
      username,
      password,
      email,
      fullname,
      gender,
      bornDate,
      address
    );
  }

  @Test
  void loginTest() {
    assertTrue(AuthService.validation(username, password).orElse(false));
    assertFalse(
      AuthService.validation("wrong_username", password).orElse(true)
    );
    assertFalse(
      AuthService.validation(username, "wrong_password").orElse(true)
    );
    assertFalse(
      AuthService.validation("wrong_username", "wrong_password").orElse(true)
    );
  }

  @Test
  void loggedUsernameTest() {
    assertNull(AuthService.getLoggedUsername());
    AuthService
      .validation(username, password)
      .ifPresent(valid -> {
        if (valid) {
          assertEquals(username, AuthService.getLoggedUsername());
        } else {
          assertNull(AuthService.getLoggedUsername());
        }
      });
    assertFalse(
      AuthService.validation(username, "wrong_password").orElse(true)
    );
    assertNull(AuthService.getLoggedUsername());

    assertFalse(
      AuthService.validation("wrong_username", password).orElse(true)
    );
    assertNull(AuthService.getLoggedUsername());

    assertFalse(
      AuthService.validation("wrong_username", "wrong_password").orElse(true)
    );
    assertNull(AuthService.getLoggedUsername());
  }
}

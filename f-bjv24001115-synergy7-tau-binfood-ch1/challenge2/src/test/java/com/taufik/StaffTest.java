package com.taufik;

import static org.junit.jupiter.api.Assertions.*;

import com.taufik.models.entities.Staff;
import com.taufik.models.enums.Gender;
import com.taufik.services.StaffService;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaffTest {

  /*
        1. Menambahkan data baru yang seusai dan lengkap dari Staff
        2. Cek ukuran data yang baru saja ditambahkan
        3. Mengambil semua data Staff
        4. Cek ukuran data semua Staff
        
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

    for (Long i = 1L; i <= 2L; i++) {
      StaffService.add(
        i,
        username,
        password,
        email,
        fullname,
        gender,
        bornDate,
        address
      );
    }
  }

  @Test
  void testCreateStaff() {
    Map<Long, Staff> allStaffs = StaffService.getAll();
    assertNotNull(allStaffs);
    assertEquals(2, allStaffs.size());

    allStaffs.forEach((id, testStaff) -> {
      assertEquals(id, testStaff.getId());
      assertEquals(username, testStaff.getUsername());
      assertEquals(password, testStaff.getPassword());
      assertEquals(email, testStaff.getEmail());
      assertEquals(fullname, testStaff.getFullname());
      assertEquals(gender, testStaff.getGender());
      assertEquals(bornDate, testStaff.getBornDate());
      assertEquals(address, testStaff.getAddress());
    });
  }

  @Test
  void testGetAllStaff() {
    Map<Long, Staff> allStaffs = StaffService.getAll();
    assertNotNull(allStaffs);
    assertEquals(2, allStaffs.size());
  }

  @Test
  void testGetStaffById() {
    for (Long i = 1L; i <= 2L; i++) {
      Staff testStaff = StaffService.getById(i);
      assertNotNull(testStaff);
      assertEquals(i, testStaff.getId());
      assertEquals(username, testStaff.getUsername());
      assertEquals(password, testStaff.getPassword());
      assertEquals(email, testStaff.getEmail());
      assertEquals(fullname, testStaff.getFullname());
      assertEquals(gender, testStaff.getGender());
      assertEquals(bornDate, testStaff.getBornDate());
      assertEquals(address, testStaff.getAddress());
    }
  }
}

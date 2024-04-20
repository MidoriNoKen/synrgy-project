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
  void testInit() {
    StaffService staffService = new StaffService();
    assertNotNull(staffService);
  }

  @Test
  void testConstructor() {
    Long id = 1L;
    Staff staff = new Staff(id, username, password, email)
      .setFullname(fullname)
      .setGender(gender)
      .setBornDate(bornDate)
      .setAddress(address);

    assertNotNull(staff);
    assertEquals(id, staff.getId());
    assertEquals(username, staff.getUsername());
    assertEquals(password, staff.getPassword());
    assertEquals(email, staff.getEmail());
    assertEquals(fullname, staff.getFullname());
    assertEquals(gender, staff.getGender());
    assertEquals(bornDate, staff.getBornDate());
    assertEquals(address, staff.getAddress());
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

    Long newId = 3L;
    String newUsername = "newTest";
    StaffService.add(
      newId,
      newUsername,
      password,
      email,
      fullname,
      gender,
      bornDate,
      address
    );
    assertEquals(3, StaffService.getAll().size());
    assertEquals(newUsername, StaffService.getById(newId).getUsername());
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

  @Test
  void testAddStaffWithNullId() {
    IllegalArgumentException exception = assertThrows(
      IllegalArgumentException.class,
      () ->
        StaffService.add(
          null,
          "newTest",
          "password",
          "newtest@mail.com",
          "New Test",
          Gender.FEMALE,
          LocalDate.of(2000, 1, 1),
          "New Address"
        )
    );

    assertEquals("ID tidak valid", exception.getMessage());
  }

  @Test
  void testAddStaffWithNullData() {
    IllegalArgumentException exception = assertThrows(
      IllegalArgumentException.class,
      () ->
        StaffService.add(
          3L,
          null,
          "password",
          "newtest@mail.com",
          "New Test",
          Gender.FEMALE,
          LocalDate.of(2000, 1, 1),
          "New Address"
        )
    );

    assertEquals("Data yang dimasukkan tidak valid", exception.getMessage());
  }

  @Test
  void testAddStaffWithDuplicateId() {
    IllegalArgumentException exception = assertThrows(
      IllegalArgumentException.class,
      () ->
        StaffService.add(
          1L,
          "newTest",
          "password",
          "newtest@mail.com",
          "New Test",
          Gender.FEMALE,
          LocalDate.of(2000, 1, 1),
          "New Address"
        )
    );

    assertEquals("ID yang dimasukkan sudah ada", exception.getMessage());
  }

  @Test
  void testAddStaff() {
    Long newId = 3L;
    String newUsername = "newTest";
    StaffService.add(
      newId,
      newUsername,
      password,
      email,
      fullname,
      gender,
      bornDate,
      address
    );
    assertEquals(3, StaffService.getAll().size());
    assertEquals(newUsername, StaffService.getById(newId).getUsername());
  }

  @Test
  void testAddWithNullData() {
    Long id = 1L;
    String username = "test", password = "test", email =
      "test@mail.com", fullname = "test", address = "test";
    LocalDate bornDate = LocalDate.of(2001, 1, 1);
    Gender gender = Gender.MALE;

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(null, null, null, null, null, null, null, null);
      }
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(id, null, null, null, null, null, null, null);
      }
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(id, username, null, null, null, null, null, null);
      }
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(id, username, password, null, null, null, null, null);
      }
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(id, username, password, email, null, null, null, null);
      }
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(
          id,
          username,
          password,
          email,
          fullname,
          null,
          null,
          null
        );
      }
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(
          id,
          username,
          password,
          email,
          fullname,
          gender,
          null,
          null
        );
      }
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(
          id,
          username,
          password,
          email,
          fullname,
          gender,
          bornDate,
          null
        );
      }
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.add(
          id,
          username,
          password,
          email,
          fullname,
          gender,
          bornDate,
          address
        );
      }
    );
  }

  @Test
  void testGetByIdWithNullId() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.getById(null);
      }
    );
  }

  @Test
  void testGetByIdWithInvalidId() {
    Long invalidId = 999L;

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        StaffService.getById(invalidId);
      },
      "ID tidak valid"
    );
  }
}

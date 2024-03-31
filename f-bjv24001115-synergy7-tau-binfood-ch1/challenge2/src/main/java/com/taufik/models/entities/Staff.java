package com.taufik.models.entities;

import com.taufik.models.enums.Gender;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class Staff extends User {

  private String fullname, address;
  private int age;
  private LocalDate bornDate;
  private Gender gender;
  private String loggedUsername;
  private static Map<Long, Staff> staffs = new HashMap<>();

  public Staff(Long id, String username, String password, String email) {
    this.setId(id);
    this.setUsername(username);
    this.setPassword(password);
    this.setEmail(email);
  }

  public static void addStaff(Staff staff) {
    staffs.put(staff.getId(), staff);
  }

  public static Map<Long, Staff> getAll() {
    return staffs;
  }

  public static Staff getById(Long id) {
    return staffs.get(id);
  }
}

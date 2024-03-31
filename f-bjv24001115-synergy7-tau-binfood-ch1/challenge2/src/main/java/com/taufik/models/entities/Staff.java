package com.taufik.models.entities;

import com.taufik.models.enums.Gender;
import java.time.LocalDate;
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

  public Staff(Long id, String username, String password, String email) {
    this.setId(id);
    this.setUsername(username);
    this.setPassword(password);
    this.setEmail(email);
  }
}

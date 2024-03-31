package com.taufik.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public abstract class User {

  private Long id;
  private String username;
  private String password;
  private String email;
}

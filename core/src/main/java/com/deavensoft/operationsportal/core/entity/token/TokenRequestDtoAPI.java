package com.deavensoft.operationsportal.core.entity.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenRequestDtoAPI {

  private String username;
  private String password;
}

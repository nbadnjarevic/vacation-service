package com.nbadnjarevic.vacationservice.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
public class User implements Serializable, BaseEntity {

  @EqualsAndHashCode.Include
  private Long id;

  private LocalDateTime createdOn;

  private LocalDateTime modifiedOn;

  private String username;

  private String password;

  private UserRole role;

}

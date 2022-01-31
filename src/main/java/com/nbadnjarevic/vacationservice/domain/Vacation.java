package com.nbadnjarevic.vacationservice.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
public class Vacation implements Serializable, BaseEntity {

  @EqualsAndHashCode.Include
  private Long id;

  private LocalDateTime createdOn;

  private LocalDateTime modifiedOn;

  private LocalDate startingDate;

  private Integer length;

  private Boolean approved;

  private User user;

}

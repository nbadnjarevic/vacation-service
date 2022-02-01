package com.nbadnjarevic.vacationservice.domain.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
public class VacationRequest {

  private LocalDate startingDate;

  private Integer length;

  private Long userId;

}

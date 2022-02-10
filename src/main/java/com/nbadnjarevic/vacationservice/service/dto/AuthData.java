package com.nbadnjarevic.vacationservice.service.dto;

import com.nbadnjarevic.vacationservice.domain.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AuthData {

	private Long id;
	private UserRole role;
	
}

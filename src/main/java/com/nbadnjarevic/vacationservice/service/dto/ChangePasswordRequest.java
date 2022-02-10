package com.nbadnjarevic.vacationservice.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ChangePasswordRequest {

	private Long id;
	private String oldPassword;
	private String newPassword;
	
}

package com.nbadnjarevic.vacationservice.service;

import com.nbadnjarevic.vacationservice.domain.Vacation;
import com.nbadnjarevic.vacationservice.domain.dto.VacationRequest;
import java.util.List;

public interface VacationService {

  public Vacation save(VacationRequest request) throws Exception;

  public Vacation getVacation(Long vacationId);

  public List<Vacation> getVacationsForUser(Long userId);

  public List<Vacation> getAllVacations();

  public Vacation approveVacation(Long vacationId);

}

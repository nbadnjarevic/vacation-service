package com.nbadnjarevic.vacationservice.service;

import com.nbadnjarevic.vacationservice.domain.Vacation;
import java.util.List;

public interface VacationService {

  public Vacation save(Vacation vacation) throws Exception;

  public Vacation getVacation(Long vacationId);

  public List<Vacation> getVacationsForUser(Long userId);

  public List<Vacation> getAllVacations();

}

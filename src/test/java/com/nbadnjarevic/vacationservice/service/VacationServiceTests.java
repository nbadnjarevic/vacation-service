package com.nbadnjarevic.vacationservice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.nbadnjarevic.vacationservice.domain.Vacation;
import com.nbadnjarevic.vacationservice.domain.dto.VacationRequest;
import com.nbadnjarevic.vacationservice.service.setup.VacationSetup;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class VacationServiceTests extends BaseServiceTests {

  @Autowired
  private VacationService vacationService;

  @Autowired
  private VacationSetup vacationSetup;

  @Test
  public void testSaveVacation() throws Exception {
    VacationRequest vacationRequest = vacationSetup.testVacation();
    Vacation vacation = vacationService.save(vacationRequest);
    assertNotNull(vacation);
  }

  @Test
  public void testGetVacation() throws Exception {
    VacationRequest vacationRequest = vacationSetup.testVacation();
    Vacation vacation = vacationService.save(vacationRequest);
    assertNotNull(vacation);

    Vacation vacation2 = vacationService.getVacation(vacation.getId());
    assertEquals(vacation2.getUserId(), vacation.getUserId());
    assertEquals(vacation2.getLength(), vacation.getLength());
    assertEquals(vacation2.getStartingDate(), vacation.getStartingDate());
    assertEquals(vacation2.getApproved(), vacation.getApproved());
  }

  @Test
  public void testGetVacationsByUserAndAll() throws Exception {
    VacationRequest vacationRequest = vacationSetup.testVacation();
    Vacation vacation = vacationService.save(vacationRequest);
    Vacation vacation2 = vacationService.save(vacationRequest);
    Vacation vacation3 = vacationService.save(vacationRequest);

    List<Vacation> userVacations = vacationService.getVacationsForUser(vacationRequest.getUserId());
    assertNotNull(userVacations);
    assertEquals(userVacations.size(), 3);

    List<Vacation> allVacations = vacationService.getAllVacations();
    assertNotNull(allVacations);
    assertEquals(allVacations.size(), 3);
  }



}

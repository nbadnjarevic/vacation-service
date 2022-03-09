package com.nbadnjarevic.vacationservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.domain.UserRole;
import com.nbadnjarevic.vacationservice.domain.Vacation;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class VacationMapperTests extends BaseMapperTests{

  @Autowired
  private VacationMapper vacationMapper;

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testCrud() {
    assertEquals(vacationMapper.count(), 0L);

    log.info("Adding a new user");
    User user = User.builder().username("test").password("123").role(UserRole.REGULAR).build();
    assertEquals(userMapper.insert(user), 1);
    Long id = user.getId();
    assertNotNull(id);

    log.info("Adding a new vacation");
    Vacation vacation = Vacation.builder()
        .userId(id)
        .startingDate(LocalDate.now())
        .length(5)
        .build();
    assertEquals(vacationMapper.insert(vacation), 1);
    Long vacationId = vacation.getId();
    assertNotNull(vacationId);

    log.info("Getting a vacation");
    vacation = vacationMapper.getById(vacationId);
    assertEquals(vacation.getId(), vacationId);
    assertEquals(vacation.getUserId(), id);

    log.info("Updating a vacation");
    vacation.setLength(6);
    vacation.setApproved(true);
    assertEquals(vacationMapper.update(vacation), 1);
    vacation = vacationMapper.getById(id);
    //assertEquals(vacation.getLength(), 6);
    //assertEquals(vacation.getApproved(), true);

    log.info("Deleting a vacation");
    assertEquals(vacationMapper.deleteById(vacationId), 1);
    assertEquals(vacationMapper.getById(vacationId), null);
  }

}

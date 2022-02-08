package com.nbadnjarevic.vacationservice.service.setup;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.domain.UserRole;
import com.nbadnjarevic.vacationservice.domain.Vacation;
import com.nbadnjarevic.vacationservice.domain.dto.VacationRequest;
import com.nbadnjarevic.vacationservice.mapper.UserMapper;
import com.nbadnjarevic.vacationservice.mapper.VacationMapper;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VacationSetup {

  @Autowired
  private VacationMapper vacationMapper;

  @Autowired
  private UserMapper userMapper;

  public VacationRequest testVacation() {
    User user = User.builder().username("test").password("123").role(UserRole.REGULAR).build();
    userMapper.insert(user);
    return VacationRequest.builder()
        .userId(user.getId())
        .startingDate(LocalDate.now())
        .length(5)
        .build();
  }

}

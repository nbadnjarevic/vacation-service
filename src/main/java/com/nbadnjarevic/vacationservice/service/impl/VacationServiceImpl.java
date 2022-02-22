package com.nbadnjarevic.vacationservice.service.impl;

import com.nbadnjarevic.vacationservice.domain.Vacation;
import com.nbadnjarevic.vacationservice.domain.dto.VacationRequest;
import com.nbadnjarevic.vacationservice.mapper.UserMapper;
import com.nbadnjarevic.vacationservice.mapper.VacationMapper;
import com.nbadnjarevic.vacationservice.service.VacationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Service
@Transactional
@Validated
public class VacationServiceImpl implements VacationService {

  final VacationMapper vacationMapper;
  final UserMapper userMapper;

  @Override
  public Vacation save(VacationRequest request) throws Exception {
    try{
      Vacation vacation = Vacation.builder()
          .startingDate(request.getStartingDate())
          .userId(request.getUserId())
          .length(request.getLength())
          .build();
      vacation = vacationMapper.save(vacation);
      return vacation;
    } catch(Exception e) {
      throw new Exception("Vacation already exists");
    }
  }

  @Override
  @Cacheable(value = "vacationCache")
  public Vacation getVacation(Long vacationId) {
    return vacationMapper.getById(vacationId);
  }

  @Override
  @Cacheable(value = "vacationCache")
  public List<Vacation> getVacationsForUser(Long userId) {
    return vacationMapper.getByUserId(userId);
  }

  @Override
  @Cacheable(value = "vacationCache")
  public List<Vacation> getAllVacations() {
    return vacationMapper.getAllVacations();
  }

}

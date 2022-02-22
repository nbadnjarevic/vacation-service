package com.nbadnjarevic.vacationservice.rest;

import com.nbadnjarevic.vacationservice.domain.dto.VacationRequest;
import com.nbadnjarevic.vacationservice.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacation")
public class VacationController {

  @Autowired
  private VacationService vacationService;

  @GetMapping(value = "/get/{id}")
  private @ResponseBody ResponseEntity<?> getVacationById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(vacationService.getVacation(id));
  }

  @PreAuthorize("#userId == authentication.principal.id")
  @GetMapping(value = "/get/user/{id}")
  private @ResponseBody ResponseEntity<?> getVacationByUserId(@PathVariable("userId") Long userId) {
    return ResponseEntity.ok(vacationService.getVacationsForUser(userId));
  }

  @GetMapping(value = "/get")
  private @ResponseBody ResponseEntity<?> getAllVacations() {
    return ResponseEntity.ok(vacationService.getAllVacations());
  }

  @PostMapping(value = "/submitRequest")
  private @ResponseBody ResponseEntity<?> submitRequest(@RequestBody VacationRequest request)
      throws Exception {
    return ResponseEntity.ok(vacationService.save(request));
  }

}

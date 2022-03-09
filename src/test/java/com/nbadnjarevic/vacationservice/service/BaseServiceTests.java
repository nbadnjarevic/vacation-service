package com.nbadnjarevic.vacationservice.service;

import com.nbadnjarevic.vacationservice.config.TestRedisConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
public class BaseServiceTests{

}

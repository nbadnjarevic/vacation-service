package com.nbadnjarevic.vacationservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@MapperScan(basePackages = { "com.nbadnjarevic.vacationservice.mapper" })
@EnableAsync
public class BaseConfig {

}
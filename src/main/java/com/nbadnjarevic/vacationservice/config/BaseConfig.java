package com.nbadnjarevic.vacationservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = { "com.nbadnjarevic.vacationservice.mapper" })
public class BaseConfig {

}
package com.nbadnjarevic.vacationservice.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

@TestConfiguration
public class TestRedisConfiguration {

  private RedisServer redisServer;

  public TestRedisConfiguration(RedisProperties redisProperties) {
    this.redisServer = RedisServer.builder().
        port(redisProperties.getRedisPort())
        .setting("maxmemory 128M")
        .build();
  }

  @PostConstruct
  public void postConstruct() {
    redisServer.start();
  }

  @PreDestroy
  public void preDestroy() {
    redisServer.stop();
  }
}
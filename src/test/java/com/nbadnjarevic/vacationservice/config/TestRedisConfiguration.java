package com.nbadnjarevic.vacationservice.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

//@TestConfiguration
public class TestRedisConfiguration {

  private RedisServer redisServer;

  public TestRedisConfiguration(@Value("${spring.redis.port}") final int redisPort) {
    this.redisServer = new RedisServer(redisPort);
  }

  @PostConstruct
  public void postConstruct() {
    this.redisServer.start();
  }

  @PreDestroy
  public void preDestroy() {
    this.redisServer.stop();
  }
}
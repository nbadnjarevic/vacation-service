package com.nbadnjarevic.vacationservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.domain.UserRole;
import com.nbadnjarevic.vacationservice.domain.Vacation;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserMapperTests extends BaseMapperTests{

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testCrud() {
    assertEquals(userMapper.count(), 0L);

    log.info("Adding a new user");
    User user = User.builder().username("test").password("123").role(UserRole.REGULAR).build();
    assertEquals(userMapper.insert(user), 1);
    Long id = user.getId();
    assertNotNull(id);

    log.info("Getting a user");
    user = userMapper.getById(id);
    assertEquals(user.getId(), id);
    assertEquals(user.getUsername(), "test");
    assertEquals(user.getRole(), UserRole.REGULAR);

    log.info("Updating a user");
    user.setRole(UserRole.ADMINISTRATOR);
    assertEquals(userMapper.update(user), 1);
    user = userMapper.getById(id);
    assertEquals(user.getRole(), UserRole.ADMINISTRATOR);

    log.info("Deleting a vacation");
    assertEquals(userMapper.deleteById(id), 1);
    assertEquals(userMapper.getById(id), null);
  }

  @Test
  public void testCreateUserWithoutRole() {
    log.info("Adding a new user");
    User user = User.builder().username("test").password("123").build();
    assertEquals(userMapper.insert(user), 1);
    Long id = user.getId();
    assertNotNull(id);

    log.info("Getting a user by id");
    user = userMapper.getById(id);
    assertEquals(user.getId(), id);
    assertEquals(user.getUsername(), "test");
    assertEquals(user.getRole(), UserRole.REGULAR);

    log.info("Getting a user by username");
    user = userMapper.getByUsername("test");
    assertEquals(user.getId(), id);
    assertEquals(user.getUsername(), "test");
    assertEquals(user.getRole(), UserRole.REGULAR);
  }

}

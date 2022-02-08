package com.nbadnjarevic.vacationservice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.service.setup.UserSetup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserServiceTests extends BaseServiceTests {

  @Autowired
  private UserService userService;

  @Autowired
  private UserSetup userSetup;

  @Test
  public void testSaveUser() throws Exception {
    User user = userSetup.testUser();
    user = userService.save(user);
    assertNotNull(user);
  }

  @Test
  public void testSaveUserWithoutRole() throws Exception {
    User user = userSetup.testUserWithoutRole();
    user = userService.save(user);
    assertNotNull(user);
  }

  @Test
  public void testSaveUser_Duplicated() throws Exception {
    User user1 = userSetup.testUser();
    userService.save(user1);

    User user2 = userSetup.testUser();
    Exception exception = assertThrows(Exception.class, () -> userService.save(user2));

    String expectedMessage = "[USERNAME_TAKEN]";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testGetUser() throws Exception {
    User user = userSetup.testUserWithoutRole();
    user = userService.save(user);
    assertNotNull(user);

    User user1 = userService.getUser(user.getId());
    assertEquals(user1.getUsername(), user.getUsername());
    assertEquals(user1.getRole(), user.getRole());
  }

}

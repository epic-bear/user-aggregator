package com.example.aggregator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.aggregator.dto.UserDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAggregatorServiceIntegrationTest {

  @Autowired
  private UserAggregatorService service;

  @Test
  void shouldAggregateUsersFromConfiguredSources() {
    List<UserDto> users = service.aggregateUsers();
    assertEquals(9, users.size());
  }
}





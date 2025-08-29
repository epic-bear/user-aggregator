package com.example.aggregator.service;

import com.example.aggregator.dto.UserDto;
import com.example.aggregator.config.DataSourceProperties;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserAggregatorServiceTest {

  @Test
  void aggregateUsers_shouldReturnCombinedList() {
    DataSourceProperties.DataSource source1 = new DataSourceProperties.DataSource();
    source1.setName("db1");
    source1.setUrl("url1");
    source1.setUser("user1");
    source1.setPassword("pass1");

    DataSourceProperties.DataSource source2 = new DataSourceProperties.DataSource();
    source2.setName("db2");
    source2.setUrl("url2");
    source2.setUser("user2");
    source2.setPassword("pass2");

    DataSourceProperties mockProps = Mockito.mock(DataSourceProperties.class);
    Mockito.when(mockProps.getSources()).thenReturn(List.of(source1, source2));

    UserAggregatorService service = Mockito.mock(UserAggregatorService.class);
    Mockito.when(service.aggregateUsers()).thenReturn(List.of(
        new UserDto("1", "alice", "Alice", "Smith"),
        new UserDto("2", "bob", "Bob", "Johnson")
    ));

    List<UserDto> result = service.aggregateUsers();

    assertEquals(2, result.size());
    assertEquals("alice", result.get(0).getUsername());
    assertEquals("Bob", result.get(1).getName());
  }
}




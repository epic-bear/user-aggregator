package com.example.aggregator.controller;

import com.example.aggregator.service.UserAggregatorService;
import com.example.aggregator.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserAggregatorService service;

  @Test
  void getUsers_shouldReturnListOfUsers() throws Exception {
    List<UserDto> mockUsers = List.of(
        new UserDto("1", "alice123", "Alice", "Smith"),
        new UserDto("2", "bob456", "Bob", "Johnson")
    );

    Mockito.when(service.aggregateUsers()).thenReturn(mockUsers);

    mockMvc.perform(get("/users")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].id").value("1"))
        .andExpect(jsonPath("$[0].username").value("alice123"))
        .andExpect(jsonPath("$[0].name").value("Alice"))
        .andExpect(jsonPath("$[0].surname").value("Smith"))
        .andExpect(jsonPath("$[1].id").value("2"))
        .andExpect(jsonPath("$[1].username").value("bob456"))
        .andExpect(jsonPath("$[1].name").value("Bob"))
        .andExpect(jsonPath("$[1].surname").value("Johnson"));
  }
}


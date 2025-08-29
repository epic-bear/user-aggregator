package com.example.aggregator.controller;

import com.example.aggregator.service.UserAggregatorService;
import com.example.aggregator.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "User management API")
@RestController
public class UserController {

  private final UserAggregatorService service;

  public UserController(UserAggregatorService service) {
    this.service = service;
  }

  @Operation(summary = "Get all users")
  @GetMapping("/users")
  public List<UserDto> getUsers() {
    return service.aggregateUsers();
  }
}


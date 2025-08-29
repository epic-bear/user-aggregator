package com.example.aggregator.service;

import com.example.aggregator.client.UserClient;
import com.example.aggregator.dto.UserDto;
import com.example.aggregator.config.DataSourceProperties;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserAggregatorService {

  private final DataSourceProperties properties;

  public UserAggregatorService(DataSourceProperties properties) {
    this.properties = properties;
  }

  public List<UserDto> aggregateUsers() {
    List<UserDto> allUsers = new ArrayList<>();

    for (DataSourceProperties.DataSource source : properties.getSources()) {
      UserClient client = new UserClient(source);
      allUsers.addAll(client.fetchUsers());
    }

    return allUsers;
  }
}


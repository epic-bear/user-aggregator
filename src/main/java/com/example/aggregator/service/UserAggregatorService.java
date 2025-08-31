package com.example.aggregator.service;

import com.example.aggregator.client.UserClient;
import com.example.aggregator.client.UserClientFactory;
import com.example.aggregator.dto.UserDto;
import com.example.aggregator.config.DataSourceProperties;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserAggregatorService {

  private final DataSourceProperties properties;
  private final UserClientFactory clientFactory;

  public UserAggregatorService(DataSourceProperties properties, UserClientFactory clientFactory) {
    this.properties = properties;
    this.clientFactory = clientFactory;
  }

  public List<UserDto> aggregateUsers() {
    List<UserDto> allUsers = new ArrayList<>();

    for (DataSourceProperties.DataSource source : properties.getSources()) {
      UserClient client = clientFactory.create(source);
      allUsers.addAll(client.fetchUsers());
    }

    return allUsers;
  }
}



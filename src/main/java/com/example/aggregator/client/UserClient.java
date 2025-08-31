package com.example.aggregator.client;

import com.example.aggregator.config.DataSourceProperties;
import com.example.aggregator.dto.UserDto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserClient {

  private final DataSourceProperties.DataSource config;

  public UserClient(DataSourceProperties.DataSource config) {
    this.config = config;
  }

  public List<UserDto> fetchUsers() {
    List<UserDto> allUsers = new ArrayList<>();
    Map<String, String> map = config.getMapping();

    String query = String.format("SELECT * FROM %s", config.getTable());

    try (Connection conn = DriverManager
        .getConnection(config.getUrl(), config.getUser(), config.getPassword());
        Statement statement = conn.createStatement();
        ResultSet usersSet = statement.executeQuery(query)) {

      while (usersSet.next()) {
        allUsers.add(new UserDto(
            usersSet.getString(map.get("id")),
            usersSet.getString(map.get("username")),
            usersSet.getString(map.get("name")),
            usersSet.getString(map.get("surname"))
        ));
      }

    } catch (SQLException e) {
      System.err.println("Error fetching users from " + config.getName() + ": " + e.getMessage());
    }

    return allUsers;
  }
}


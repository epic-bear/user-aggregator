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
    List<UserDto> users = new ArrayList<>();
    Map<String, String> map = config.getMapping();

    String query = String.format("SELECT * FROM %s", config.getTable());

    try (Connection conn = DriverManager
        .getConnection(config.getUrl(), config.getUser(), config.getPassword());
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        users.add(new UserDto(
            rs.getString(map.get("id")),
            rs.getString(map.get("username")),
            rs.getString(map.get("name")),
            rs.getString(map.get("surname"))
        ));
      }

    } catch (SQLException e) {
      System.err.println("Error fetching users from " + config.getName() + ": " + e.getMessage());
    }

    return users;
  }
}


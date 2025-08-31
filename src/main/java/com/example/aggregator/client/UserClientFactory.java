package com.example.aggregator.client;

import com.example.aggregator.config.DataSourceProperties;
import org.springframework.stereotype.Component;

@Component
public class UserClientFactory {

  public UserClient create(DataSourceProperties.DataSource source) {
    return new UserClient(source);
  }
}


package com.example.aggregator.config;


import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "data-sources")
@Data
public class DataSourceProperties {
  private List<DataSource> sources;

  @Data
  public static class DataSource {
    private String name;
    private String strategy;
    private String url;
    private String table;
    private String user;
    private String password;
    private Map<String, String> mapping;
  }
}


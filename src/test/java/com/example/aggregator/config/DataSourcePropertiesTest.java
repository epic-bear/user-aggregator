package com.example.aggregator.config;

import com.example.aggregator.config.DataSourceProperties.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DataSourcePropertiesTest {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
      .withUserConfiguration(TestConfig.class)
      .withPropertyValues(
          "data-sources.sources[0].name=data-base-1",
          "data-sources.sources[0].strategy=postgres",
          "data-sources.sources[0].url=jdbc:postgresql://localhost:5432/db1",
          "data-sources.sources[0].table=users",
          "data-sources.sources[0].user=testuser",
          "data-sources.sources[0].password=testpass",
          "data-sources.sources[0].mapping.id=user_id",
          "data-sources.sources[0].mapping.username=login",
          "data-sources.sources[0].mapping.name=first_name",
          "data-sources.sources[0].mapping.surname=last_name"
      );

  @Test
  void shouldBindYamlToDataSourceProperties() {
    contextRunner.run(context -> {
      DataSourceProperties props = context.getBean(DataSourceProperties.class);
      List<DataSource> sources = props.getSources();

      assertThat(sources).hasSize(1);
      DataSource ds = sources.get(0);
      assertThat(ds.getName()).isEqualTo("data-base-1");
      assertThat(ds.getStrategy()).isEqualTo("postgres");
      assertThat(ds.getUrl()).isEqualTo("jdbc:postgresql://localhost:5432/db1");
      assertThat(ds.getTable()).isEqualTo("users");
      assertThat(ds.getUser()).isEqualTo("testuser");
      assertThat(ds.getPassword()).isEqualTo("testpass");
      assertThat(ds.getMapping()).containsEntry("id", "user_id");
    });
  }

  @EnableConfigurationProperties(DataSourceProperties.class)
  static class TestConfig {

  }
}


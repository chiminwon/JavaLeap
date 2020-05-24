package com.ming.config;

import com.ming.dbutils.OtherRoutingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.jdbc.DataSourceBuilder.create;

@Configuration
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.master")
  public DataSource masterDataSource() {
    return create().build();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.slave")
  public DataSource slaveDataSource() {
    return create().build();
  }

  @Bean
  public DataSource otherDataSource(
      @Qualifier("masterDataSource") DataSource masterDataSource,
      @Qualifier("slaveDataSource") DataSource slaveDataSource) {
      Map<Object, Object> targetDataSource = new HashMap<>();
      targetDataSource.put(OtherRoutingDataSource.MASTER, masterDataSource);
      targetDataSource.put(OtherRoutingDataSource.SLAVE, slaveDataSource);
      OtherRoutingDataSource otherRoutingDataSource = new OtherRoutingDataSource();
      otherRoutingDataSource.setTargetDataSources(targetDataSource);
      otherRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
      return null;
  }
}

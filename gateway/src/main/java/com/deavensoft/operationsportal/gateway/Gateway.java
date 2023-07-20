package com.deavensoft.operationsportal.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Gateway {

  public static void main(String[] args) {
    SpringApplication.run(Gateway.class, args);
  }
}

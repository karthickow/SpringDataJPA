package com.karthick.jpaonetomany;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaOneToManyApplication {

  public static void main(String[] args) {
    SpringApplication.run(JpaOneToManyApplication.class, args);
  }

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getDefault());
  }
}

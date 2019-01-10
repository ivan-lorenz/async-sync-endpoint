package com.schibsted.asyncsyncendpoint.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/*
 Feign configuration of concrete feign clients MUST be outside componentScan:
 http://projects.spring.io/spring-cloud/spring-cloud.html#spring-cloud-feign-overriding-defaults
*/
@SpringBootApplication(scanBasePackages = {
    "com.schibsted.asyncsyncendpoint.framework.controller"
    })
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableSwagger2
@EnableWebSecurity
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

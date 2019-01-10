package com.schibsted.asyncsyncendpoint.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class HelloWorldSimpleController {
  @RequestMapping("/hello-simple")
  @GetMapping
  public String sayHello() throws InterruptedException {
    return doAwesomeThingsAndGreet();
  }

  private String doAwesomeThingsAndGreet() throws InterruptedException {
    Thread.sleep(200);
    return "Hello World";
  }
}

package com.schibsted.asyncsyncendpoint.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class HelloWorldController {
  @RequestMapping("/hello")
  @GetMapping
  public String sayHello() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future = doAwesomeThingsAndGreet();
    return future.get();
  }

  private CompletableFuture<String> doAwesomeThingsAndGreet() {
    return CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(200);
        return "Hello world";
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
  }
}

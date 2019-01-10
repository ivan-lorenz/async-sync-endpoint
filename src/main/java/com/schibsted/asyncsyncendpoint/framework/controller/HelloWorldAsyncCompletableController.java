package com.schibsted.asyncsyncendpoint.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class HelloWorldAsyncCompletableController {
  @RequestMapping("/hello-async-completable")
  @GetMapping
  public CompletableFuture<String> sayHello() {
    return doAwesomeThingsAndGreet();
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

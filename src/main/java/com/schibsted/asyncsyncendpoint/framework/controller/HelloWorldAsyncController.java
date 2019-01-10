package com.schibsted.asyncsyncendpoint.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@RestController
public class HelloWorldAsyncController {
  @RequestMapping("/hello-async")
  @GetMapping
  public DeferredResult<String> sayHello() {
    DeferredResult<String> deffered = new DeferredResult<>(1000L);
    CompletableFuture<String> future = doAwesomeThingsAndGreet();
    future.whenComplete((res, ex) -> {
      if (ex != null) {
        deffered.setErrorResult(ex);
      } else {
        deffered.setResult(res);
      }
    });
    return deffered;
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

package com.schibsted.asyncsyncendpoint.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

@RestController
public class HelloWorldControllerRx {
  @RequestMapping("/hello-rx")
  @GetMapping
  public String sayHello() {
    return doAwesomeThingsAndGreet().toBlocking().value();
  }

  @SuppressWarnings("Duplicates")
  private Single<String> doAwesomeThingsAndGreet() {
    return Single.<String>create(emitter -> {
      try {
        System.out.println(Thread.currentThread().getName() + "I'm RX!!!!!!");
        Thread.sleep(200);
        emitter.onSuccess("Hello World");
      } catch (InterruptedException e) {
       emitter.onError(e);
      }
    }).subscribeOn(Schedulers.from(ForkJoinPool.commonPool()));
  }
}

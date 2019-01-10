package com.schibsted.asyncsyncendpoint.framework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.concurrent.ForkJoinPool;

@RestController
public class HelloWorldAsyncControllerRxSingle {
  @RequestMapping("/hello-rxasync-single")
  @GetMapping
  public Single<ResponseEntity<String>> sayHello() {
    return doAwesomeThingsAndGreet()
        .map(string -> new ResponseEntity<>(string, HttpStatus.OK));
  }

  @SuppressWarnings("Duplicates")
  private Single<String> doAwesomeThingsAndGreet() {
    return Single.<String>create(emitter -> {
      try {
        Thread.sleep(200);
        emitter.onSuccess("Hello World");
      } catch (InterruptedException e) {
       emitter.onError(e);
      }
    }).subscribeOn(Schedulers.from(ForkJoinPool.commonPool()));
  }
}

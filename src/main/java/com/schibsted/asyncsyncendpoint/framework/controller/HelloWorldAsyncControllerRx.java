package com.schibsted.asyncsyncendpoint.framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.concurrent.ForkJoinPool;

@RestController
public class HelloWorldAsyncControllerRx {
  @RequestMapping("/hello-rxasync")
  @GetMapping
  public DeferredResult<String> sayHello() {
    DeferredResult<String> deferred = new DeferredResult<>(1000L);
    doAwesomeThingsAndGreet()
        .subscribe(deferred::setResult, deferred::setErrorResult);
    return deferred;
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

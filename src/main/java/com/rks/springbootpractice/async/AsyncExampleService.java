package com.rks.springbootpractice.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class AsyncExampleService {

    /*
    * For Async the method should be public because in case of private method proxy will not work
    * Because Spring create proxy Object around the service class and it will only able to intercept public method.
    * Also Async method should get called from different class otherwise proxy will not apply because
    * it will be a normal method call.
    * */
    @Async
    public void performAsyncTask() {
        // Your asynchronous logic here
        System.out.println("Executing task asynchronously - " + Thread.currentThread().getName());
    }

    @Async
    public Future<String> asyncMethodWithReturnType() {
        // Simulate a long-running task
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle exception
        }
        return new AsyncResult<>("Task's Result");
    }

    @Async//("taskExecutor")
    public Future<String> asyncMethodWithCustomTaskExecutor() {
        log.info(Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle exception
        }
        return new AsyncResult<>("Task's Result");
    }
}

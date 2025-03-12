package com.rks.springbootpractice.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Slf4j
public class AsyncChainExampleService {

    @Async
    public CompletableFuture<String> fetchDataFromDb() {
        // Simulate a database fetch with a delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle exception
        }
        return CompletableFuture.completedFuture("Fetch data from DB");
    }

    @Async
    public CompletableFuture<String> callExternalApi(String data) {
        // Simulate an external API call with a delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle exception
        }
        return CompletableFuture.completedFuture("Response from External API using " + data);
    }

    @Async
    public CompletableFuture<String> computeResult(String apiResponse) {
        // Simulate computation with a delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle exception
        }
        String result = "Computed result based on " + apiResponse;
        return CompletableFuture.completedFuture(result);
    }
}

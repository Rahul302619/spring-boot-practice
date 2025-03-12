package com.rks.springbootpractice.async;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("async")
public class AsynExampleController {

    private AsyncExampleService asyncExampleService;
    private AsyncChainExampleService asyncChainExampleService;

    @GetMapping("/startAsyncTask")
    public String startAsyncTask() {
        asyncExampleService.performAsyncTask();
        return "Task has been started!";
    }

    @GetMapping("/startAsyncTaskWithResult")
    public String startAsyncTaskWithResult() throws ExecutionException, InterruptedException {
        Future<String> result = asyncExampleService.asyncMethodWithReturnType();
        return "Result from async task: " + result.get();
    }

    @GetMapping("/custom-task-executor")
    public String customTaskExecutor() throws ExecutionException, InterruptedException {
        Future<String> result = asyncExampleService.asyncMethodWithCustomTaskExecutor();
        return "Result from async task: " + result.get();
    }

    @GetMapping("/chain-task")
    public CompletableFuture<String> chainTask() {
        var future =  asyncChainExampleService.fetchDataFromDb()
                .thenCompose(data -> asyncChainExampleService.callExternalApi(data))
                .thenCompose(data -> asyncChainExampleService.computeResult(data));

        // If no exception occurs, the result of the CompletableFuture is returned as-is
        return future.exceptionally(ex -> {
            // This callback will not be invoked if no exception occurs during CompletableFuture execution
            // Log the exception (if any)
            // Return an appropriate error response (if any)
            return "An error occurred: " + ex.getMessage();
        });
    }

    @GetMapping("/execute-two-task-parallel")
    public String performTasks() {
        /*
        * Here fetchDataFromDb() and callExternalApi() execute parallely and the combine response of them will be used by computeResult()
        * and after that we are using join() to wait for the response
        * */
        return asyncChainExampleService.fetchDataFromDb()
                .thenCombine(asyncChainExampleService.callExternalApi("Test"),
                        (dbData, apiResponse) -> "Computed result based on DB data: " + dbData + " and API response: " + apiResponse)
                .thenCompose(data -> asyncChainExampleService.computeResult(data))
                .join();
    }
}

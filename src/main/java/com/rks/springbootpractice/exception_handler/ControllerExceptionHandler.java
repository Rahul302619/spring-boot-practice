package com.rks.springbootpractice.exception_handler;

import com.rks.springbootpractice.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage runtimeExceptionHandler(RuntimeException exception, HttpServletRequest request) {
        return new ErrorMessage(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        LocalDateTime.now(),
                        exception.getMessage(),
                        request.getRequestURI());
    }
}

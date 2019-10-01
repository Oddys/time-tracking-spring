package org.oddys.timetrackingspring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({MissingServletRequestParameterException.class,
                      MethodArgumentTypeMismatchException.class,
                      ConstraintViolationException.class})
    public void handleParameterException(Throwable throwable, HttpServletResponse response)
            throws IOException {
        log.error(throwable.toString());
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Throwable.class)
    public void handleThrowable(Throwable throwable, HttpServletResponse response)
            throws IOException {
        log.error(throwable.toString());
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}

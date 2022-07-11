package com.bysesoft.bysesoft.common.handler;

import com.bysesoft.bysesoft.common.ConflictException;
import com.bysesoft.bysesoft.common.NotFoundException;
import com.bysesoft.bysesoft.common.error.ErrorCode;
import com.bysesoft.bysesoft.common.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorDetails> handleNotFound(NotFoundException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .detail(" %s.".formatted(ex.getResourceId()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConflictException.class)
    private ResponseEntity<ErrorDetails> handleConflict(ConflictException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_ALREADY_EXISTS)
                .detail(ex.getErrorMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
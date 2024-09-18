package org.vuetiful.DNS.global.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.vuetiful.DNS.global.exception.ErrorCode;
import org.vuetiful.DNS.global.exception.ErrorResponse;
import org.vuetiful.DNS.global.exception.GlobalException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(GlobalException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.name(),
                errorCode.getHttpStatus(),
                errorCode.getMessage()
        );

        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }
}

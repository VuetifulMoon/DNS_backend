package org.vuetiful.DNS.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String code;
    private HttpStatus status;
    private String message;
}

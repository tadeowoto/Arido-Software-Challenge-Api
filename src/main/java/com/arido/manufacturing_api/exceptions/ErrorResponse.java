package com.arido.manufacturing_api.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String errorDetails;

    public ErrorResponse(String errorDetails,  int statusCode, String message) {
        this.errorDetails = errorDetails;
        this.statusCode = statusCode;
        this.message = message;
    }
}

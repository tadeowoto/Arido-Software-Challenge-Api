package com.arido.manufacturing_api.exceptions;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String errorDetails;

    public ErrorResponse(String errorDetails, int statusCode, String message) {
        this.errorDetails = errorDetails;
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}

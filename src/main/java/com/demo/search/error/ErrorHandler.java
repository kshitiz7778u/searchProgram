package com.demo.search.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorHandler {

    private String errorCode;
    private String errorMessage;
    private String statusCode;

}

package com.self_discovery.self_discovery.selfdiscovery.self_discovery.ExceptionHandler;


import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L; // Add this line
    private static final int DEFAULT_STATUS_CODE = HttpStatusCodes.CONFLICT;


    public CustomException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return DEFAULT_STATUS_CODE;
    }
}


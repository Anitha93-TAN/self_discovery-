package com.self_discovery.self_discovery.selfdiscovery.ExceptionHandler;


import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;

import java.io.Serializable;

public class ConflictException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L; // Add this line
    
    private static final int DEFAULT_STATUS_CODE = HttpStatusCodes.NOT_FOUND;



    public ConflictException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return DEFAULT_STATUS_CODE;
    }
}

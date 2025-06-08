package com.self_discovery.self_discovery.selfdiscovery.ExceptionHandler;



import com.self_discovery.self_discovery.selfdiscovery.utils.HttpStatusCodes;

import java.io.Serializable;


public class NotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_STATUS_CODE = HttpStatusCodes.NOT_FOUND;



    public NotFoundException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return DEFAULT_STATUS_CODE;
    }
}
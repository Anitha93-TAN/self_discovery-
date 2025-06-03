package com.self_discovery.self_discovery.selfdiscovery.utils;

public interface ResponseHandler<T> {
	ApiResponse<T> success(T data, String message, int status);

	ApiResponse<T> success(String message, int status);
	
	ApiResponse<T> success(T data, String message);


	ApiResponse<T> error(String message, int status);
}

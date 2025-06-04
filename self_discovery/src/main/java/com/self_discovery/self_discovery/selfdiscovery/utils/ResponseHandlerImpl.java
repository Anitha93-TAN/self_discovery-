package com.self_discovery.self_discovery.selfdiscovery.utils;

import org.springframework.stereotype.Component;

@Component
public class ResponseHandlerImpl<T> implements ResponseHandler<T> {

//    @Override
//    public ApiResponse<T> success(T data, String message) {
//        return new ApiResponse<>(HttpStatus.OK.value(), message, data);
//    }

    @Override
    public ApiResponse<T> error(String message, int status) {
        return new ApiResponse<>(status, message, null);
    }



	@Override
	public ApiResponse<T> success(T data, String message, int status) {
		// TODO Auto-generated method stub
		return new ApiResponse<>(status, message, data);

	}
	
	@Override
	public ApiResponse<T> success(T data, String message) {
		// TODO Auto-generated method stub
		return new ApiResponse<>(HttpStatusCodes.OK, message, data);
		
	}



	@Override
	public ApiResponse<T> success(String message, int status) {
		// TODO Auto-generated method stub
		return new ApiResponse<>(HttpStatusCodes.OK, message, null);
	}
}

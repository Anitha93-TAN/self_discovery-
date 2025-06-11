package com.self_discovery.self_discovery.selfdiscovery.ExceptionHandler;


import com.self_discovery.self_discovery.selfdiscovery.utils.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler{


	// Handle HttpRequestMethodNotSupportedException
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResponse<?>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		ApiResponse<?> response = new ApiResponse<>();
		response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		response.setMessage("Request method '" + ex.getMethod() + "' is not supported for this endpoint.");
		response.setData(null);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
	}

	// Handle MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		ApiResponse<Map<String, String>> response = new ApiResponse<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage("Validation failed for one or more fields.");
		response.setData(errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	// Handle DataIntegrityViolationException
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		ApiResponse<?> response = new ApiResponse<>();
		response.setStatus(HttpStatus.CONFLICT.value());
		response.setMessage("Data integrity violation: " + ex.getRootCause().getMessage());
		response.setData(null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}
	

	// Handle MissingServletRequestParameterException
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiResponse<?>> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex) {
		ApiResponse<?> response = new ApiResponse<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage("Missing required parameter: " + ex.getParameterName());
		response.setData(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	// Handle ConstraintViolationException (e.g., @Valid violations in service or
	// entity classes)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolationException(
			ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getConstraintViolations()
				.forEach(error -> errors.put(error.getPropertyPath().toString(), error.getMessage()));

		ApiResponse<Map<String, String>> response = new ApiResponse<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage("Validation error occurred.");
		response.setData(errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	// Handle MethodArgumentTypeMismatchException
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse<?>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		ApiResponse<?> response = new ApiResponse<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage("Type mismatch for parameter '" + ex.getName() + "'. Expected type: "
				+ ex.getRequiredType().getSimpleName());
		response.setData(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	// Handle NoHandlerFoundException (e.g. resource not found when incorrect URL is
	// accessed)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleNoHandlerFound(NoHandlerFoundException ex, WebRequest request) {
		ApiResponse<?> response = new ApiResponse<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("The requested URL " + ex.getRequestURL() + " was not found on this server.");
		response.setData(null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	// Handle DataAccessException (e.g. database-related issues)
	@ExceptionHandler(org.springframework.dao.DataAccessException.class)
	public ResponseEntity<ApiResponse<?>> handleDatabaseExceptions(org.springframework.dao.DataAccessException ex) {
		ApiResponse<?> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("A database error occurred: " + ex.getMessage());
		response.setData(null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	// Handle IllegalArgumentException (e.g. invalid arguments passed to a method)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
		ApiResponse<?> response = new ApiResponse<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage(ex.getMessage());
		response.setData(null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	// Handle IllegalStateException (e.g. invalid states passed to a method)
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiResponse<?>> handleIllegalStateException(IllegalStateException ex) {
	    ApiResponse<?> response = new ApiResponse<>();
	    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	    response.setMessage("An illegal state occurred: " + ex.getMessage());
	    response.setData(null);
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	// Handle UsernameNotFoundException (e.g., user details not found)
	/* @ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
	    ApiResponse<?> response = new ApiResponse<>();
	    response.setStatus(HttpStatusCodes.CONFLICT);
	    response.setMessage("User not found: " + ex.getMessage());
	    response.setData(null);
	    return ResponseEntity.status(HttpStatusCodes.CONFLICT).body(response);
	}*/


	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
	    ApiResponse<?> response = new ApiResponse<>();
	    response.setStatus(HttpStatus.FORBIDDEN.value());
	    response.setMessage("Access is denied: " + ex.getMessage());
	    response.setData(null);
	    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
	

	/*@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ApiResponse<?>> handleUnAuthoorizeException(UsernameNotFoundException ex) {
	    ApiResponse<?> response = new ApiResponse<>();
	    response.setStatus(HttpStatus.UNAUTHORIZED.value());
	    response.setMessage("User not authorized: " + ex.getMessage());
	    response.setData(null);
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
	*/

	// Handle generic Exception (catch-all for unexpected errors)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
		ApiResponse<?> response = new ApiResponse<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("An unexpected error occurred: " + ex.getMessage());
		response.setData(null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	


}

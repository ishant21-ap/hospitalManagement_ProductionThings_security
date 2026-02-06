package com.project.hospitalManagement_ProductionThings.error;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Here we are resolving error which are coming on SPring MVC, but what if we get error in filter
    // lets say for example we get error in JWTAuthFilter, how can we handle them ?
    // For that we are using HandleExceptionResolver in JWTAuthFilter, it will Transfer whatever exception caught
    // there to this GlobalExceptionHandler

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError>  handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ApiError apiError = new ApiError("Username not found with username : " + ex.getMessage(), HttpStatus.NOT_FOUND);
        return new  ResponseEntity<>(apiError, apiError.getStatusCode());
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError>  handleAuthenticationException(AuthenticationException ex) {
        ApiError apiError = new ApiError("Authentication Failed : " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new  ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError>  handleJwtException(JwtException ex) {
        ApiError apiError = new ApiError("JWT Token is invalid : " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new  ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError>  handleAccessDeniedException(AccessDeniedException ex) {
        ApiError apiError =  new ApiError("Access Denied : " + ex.getMessage(), HttpStatus.FORBIDDEN);
        return new  ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError>  handleException(Exception ex) {
        ApiError apiError = new ApiError("Exception : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new  ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

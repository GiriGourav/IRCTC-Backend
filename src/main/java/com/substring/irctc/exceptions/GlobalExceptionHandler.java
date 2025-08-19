package com.substring.irctc.exceptions;

import com.substring.irctc.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class GlobalExceptionHandler {

//    pure project ka lie hai
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity <ErrorResponse>handleNoSuchException(NoSuchElementException exception)
    {

        ErrorResponse response= new ErrorResponse("Train not Found !! " + exception.getMessage(),"404",false);

        ResponseEntity <ErrorResponse> responseResponseEntity=new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return responseResponseEntity;

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <ErrorResponse>handleResourceNotFoundException(ResourceNotFoundException exception)
    {

        ErrorResponse response= new ErrorResponse( exception.getMessage(),"404",false);

        ResponseEntity <ErrorResponse> responseResponseEntity=new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return responseResponseEntity;

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity <ErrorResponse>handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception)
    {
        String message=exception.getMessage().contains("Duplicate entry")?"You are trying to provide field that are all ready in database": exception.getMessage();

        ErrorResponse response= new ErrorResponse( message,"400",false);

        ResponseEntity <ErrorResponse> responseResponseEntity=new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return responseResponseEntity;

    }

//    handle Method Argument Not Valid Exception

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        Map<String,String> errorResponse=new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error->{
            String errorMessage=error.getDefaultMessage();
            String field=((FieldError) error).getField();
            errorResponse.put(field, errorMessage);
        });
        ResponseEntity<Map<String,String>> error=new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return error;
    }
}

package com.yi.online_bookshop.api_response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yi.online_bookshop.api_response.suberrors.ApiSubError;
import com.yi.online_bookshop.api_response.suberrors.ApiValidationError;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GeneralApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ApiSubError> fieldErrors = new ArrayList<>();
        List<FieldError> errors = new ArrayList<>(ex.getBindingResult().getFieldErrors());

        Collections.sort(errors, Comparator.comparing(FieldError::getField));

        for (FieldError error : errors) {
            fieldErrors.add(new ApiValidationError(error.getField(), error.getDefaultMessage()));
        }

        GeneralApiResponse response = new GeneralApiResponse(HttpStatus.BAD_REQUEST, "Validation failed.", fieldErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

package com.dtn.online.compiler.coderunner.hanlders;

import com.dtn.online.compiler.coderunner.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@RestControllerAdvice
public class ControllerAdvise {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException ex){
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String field = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(field, errorMessage);
        });
        return new ResponseEntity( ApiResponse.builder()
                        .data(null)
                        .error( errors )
                        .success( false )
                        .build(),   HttpStatus.BAD_REQUEST
        );
    }
}

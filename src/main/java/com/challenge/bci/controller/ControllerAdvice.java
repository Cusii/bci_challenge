package com.challenge.bci.controller;

import com.challenge.bci.dto.ErrorDTO;
import com.challenge.bci.dto.ErrorDetail;
import com.challenge.bci.exception.RequestExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RequestExceptions.class)
    public ResponseEntity<ErrorResponse> requestExceptionHandler(RequestExceptions ex) {
        ErrorDTO error = new ErrorDTO();
        error.setTimestamp(ex.getTimestamp());
        error.setCodigo(ex.getCodigo());
        error.setDetail(ex.getMessage());

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(error);

        ErrorResponse errorResponse = new ErrorResponse(errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getCodigo()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDTO errorDetail = new ErrorDTO();

        final ErrorDetail errors = new ErrorDetail();

        errorDetail.setCodigo(400);
        errorDetail.setDetail(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        errorDetail.setTimestamp(new Date());

        Set<ErrorDTO> setErros= new HashSet<>();
        setErros.add(errorDetail);
        errors.setErrors(setErros);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    public static class ErrorResponse {
        private List<ErrorDTO> error;

        public ErrorResponse(List<ErrorDTO> error) {
            this.error = error;
        }

        public List<ErrorDTO> getError() {
            return error;
        }

        public void setError(List<ErrorDTO> error) {
            this.error = error;
        }
    }
}

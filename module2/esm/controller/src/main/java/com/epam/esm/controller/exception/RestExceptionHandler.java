package com.epam.esm.controller.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityAlreadyExistsControllerException.class})
    public ResponseEntity<ResponseEntityException> onDuplicateEntity(EntityAlreadyExistsControllerException exception) {
        ResponseEntityException response = ResponseEntityException.builder()
                .errorCode(EntityAlreadyExistsControllerException.ERROR_CODE)
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity.status(403)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler({EntityIsNotValidControllerException.class})
    public ResponseEntity<ResponseEntityException> onEntityInvalid(EntityIsNotValidControllerException exception) {
        ResponseEntityException response = ResponseEntityException.builder()
                .errorCode(EntityAlreadyExistsControllerException.ERROR_CODE)
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseEntityException response = ResponseEntityException.builder()
                .errorCode(status.value())
                .errorMessage("Bad request")
                .build();
        return ResponseEntity.status(response.getErrorCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseEntityException response = ResponseEntityException.builder()
                .errorCode(status.value())
                .errorMessage("Unsupported media type")
                .build();
        return ResponseEntity.status(response.getErrorCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}

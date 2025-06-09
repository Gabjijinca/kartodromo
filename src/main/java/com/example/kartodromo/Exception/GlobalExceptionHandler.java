package com.example.kartodromo.Exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErroResponse> tratarDuplicidade(DuplicateException ex) {
        var erroDTO = ErroResponse.conflito(ex.getMessage());
        return ResponseEntity.status(erroDTO.status()).body(erroDTO);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<ErroCampo> lisaErros = fieldErrors.stream().map(fe -> new ErroCampo(fe.getField(),fe.getDefaultMessage())).collect(Collectors.toList());
        return new ErroResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação",lisaErros);
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResponse handleNotFoundException(NotFoundException ex) {
        return new ErroResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
    }



}

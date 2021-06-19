package br.com.proposta.microservico.common.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomHandlerException {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrosFormularioDTO>> handler(MethodArgumentNotValidException exception) {
        List<ErrosFormularioDTO> errosFormularioDTO = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            if (fieldError.getDefaultMessage().equals("O atributo está repetido")) {
                errosFormularioDTO.add(new ErrosFormularioDTO(fieldError.getField(), fieldError.getDefaultMessage()));
                return new ResponseEntity<>(errosFormularioDTO, HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                errosFormularioDTO.add(new ErrosFormularioDTO(fieldError.getField(), fieldError.getDefaultMessage()));
                return new ResponseEntity<>(errosFormularioDTO, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(errosFormularioDTO, HttpStatus.BAD_REQUEST);
    }
}

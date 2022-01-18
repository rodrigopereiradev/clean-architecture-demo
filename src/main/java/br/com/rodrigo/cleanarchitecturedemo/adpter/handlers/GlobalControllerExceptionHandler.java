package br.com.rodrigo.cleanarchitecturedemo.adpter.handlers;

import br.com.rodrigo.cleanarchitecturedemo.adpter.dtos.ErrorDTO;
import br.com.rodrigo.cleanarchitecturedemo.domain.exceptions.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> unknownExceptionHandle(Exception ex) {
        var error = getInternalServerError(ex);
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDTO> productExceptionHandle(ProductException ex) {
        var error = getInternalServerError(ex);
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException ex) {
        var error = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                getValidationErrorsMessages(ex.getBindingResult())
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> entityNotFoundExceptionHandle(EntityNotFoundException ex) {
        var error = new ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage()
        );
        return ResponseEntity.badRequest().body(error);
    }

    private ErrorDTO getInternalServerError(Exception ex) {
        return new ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage()
        );
    }

    private List<String> getValidationErrorsMessages(BindingResult bindingResult) {
        if (bindingResult.getAllErrors().isEmpty())
            return Collections.singletonList("It was not possible identify the error.");

        return bindingResult
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

    }
}

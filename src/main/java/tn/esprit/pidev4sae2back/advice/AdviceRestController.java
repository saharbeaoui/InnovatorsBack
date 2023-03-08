package tn.esprit.pidev4sae2back.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tn.esprit.pidev4sae2back.exception.BadWordException;

@RestControllerAdvice
public class AdviceRestController {
    @ExceptionHandler(BadWordException.class)
    public ResponseEntity<String> handleBadWordException(BadWordException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

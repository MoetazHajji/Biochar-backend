package tn.esprit.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<?> resourceNotFoundHandling(MessagingException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                LocalTime.now(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    // handling specific exception
    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandling(RessourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                LocalTime.now(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    //an I/O error occurs accessing information using streams, files and directories.
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> ExceptionAccessingInformationFilesAndDirectories(IOException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now() ,
                LocalTime.now(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    // handling global exception

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ErrorDetails errorDetails =   new ErrorDetails(LocalDate.now(),
                LocalTime.now(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
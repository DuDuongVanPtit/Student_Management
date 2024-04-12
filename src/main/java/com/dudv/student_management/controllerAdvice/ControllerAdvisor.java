package com.dudv.student_management.controllerAdvice;

import com.dudv.student_management.customException.DateException;
import com.dudv.student_management.customException.FieldRequiredException;
import com.dudv.student_management.customException.GradeException;
import com.dudv.student_management.customException.NameException;
import com.dudv.student_management.model.errorResponseModel.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> FieldException(FieldRequiredException e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        String[] errorDetails = e.getMessage().split("!");
        errorResponseDTO.setError(errorDetails[0]);
        List<String> details = new ArrayList<>();
        details.add("You must fill out all fields completely");
        String[] detail = errorDetails[1].split(" ");
        for(String d : detail) details.add(d);
        errorResponseDTO.setDetail(details);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GradeException.class)
    public ResponseEntity<Object> gradeException(GradeException e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError(e.getMessage());
        List<String> details = new ArrayList<>();
        details.add("The allowed average mark range is from 0.0 to 10.0");
        details.add("The value must be the number");
        errorResponseDTO.setDetail(details);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateException.class)
    public ResponseEntity<Object> dateException(DateException e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError(e.getMessage());
        List<String> details = new ArrayList<>();
        details.add("The correct date format is: yyyy-dd-MM, please check!");
        details.add("Your date of birth can't be after the current time");
        errorResponseDTO.setDetail(details);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NameException.class)
    public ResponseEntity<Object> NameException(NameException e){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setError(e.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Your name should be less than 50 characters");
        errorResponseDTO.setDetail(details);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

}

package com.dudv.student_management.customException;

import com.dudv.student_management.model.errorResponseModel.ErrorResponseDTO;

public class FieldRequiredException extends RuntimeException{
    public FieldRequiredException(String s) {
        super(s);
    }
}

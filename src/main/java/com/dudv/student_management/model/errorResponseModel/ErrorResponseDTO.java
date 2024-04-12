package com.dudv.student_management.model.errorResponseModel;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ErrorResponseDTO {
    private String error;
    private List<String> detail = new ArrayList<>();
}

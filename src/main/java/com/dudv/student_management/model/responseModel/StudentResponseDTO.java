package com.dudv.student_management.model.responseModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentResponseDTO {
    private String fullName;
    private String hometown;
    private String gender;
    private Double averageMark;
    private String birthday;
    private String faculty;
    private String className;
}

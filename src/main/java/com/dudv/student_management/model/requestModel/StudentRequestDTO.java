package com.dudv.student_management.model.requestModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentRequestDTO {
    private String fullName;
    private String hometown;
    private String gender;
    private String averageMark;
    private String birthday;
    private String faculty;
    private String className;
}

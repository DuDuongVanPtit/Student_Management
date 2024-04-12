package com.dudv.student_management.model.responseModel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BirthdayResponeDTO {
    private String fullName;
    private String faculty;
    private String className;
    private Date birthday;
    private String message;
}

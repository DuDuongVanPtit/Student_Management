package com.dudv.student_management.converter;

import com.dudv.student_management.customException.DateException;
import com.dudv.student_management.entity.StudentEntity;
import com.dudv.student_management.model.requestModel.StudentRequestDTO;
import com.dudv.student_management.model.responseModel.BirthdayResponeDTO;
import com.dudv.student_management.model.responseModel.StudentResponseDTO;
import com.dudv.student_management.validation.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class StudentConverter {
    @Autowired
    private StudentValidation studentValidation;
    public StudentEntity toStudentEntity(StudentRequestDTO studentRequestDTO){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFullName(studentRequestDTO.getFullName());
        studentEntity.setGender(studentRequestDTO.getGender());
        studentEntity.setHomeTown(studentRequestDTO.getHometown());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        Date birthday = null;
        try {
            birthday =sdf.parse(studentRequestDTO.getBirthday());
        } catch (ParseException e) {
            throw new DateException("Date exception");
        }
        studentEntity.setBirthday(birthday);
        studentValidation.gradeValidation(studentRequestDTO.getAverageMark());
        studentEntity.setAverageMark(Double.parseDouble(studentRequestDTO.getAverageMark()));
        studentEntity.setClassName(studentRequestDTO.getClassName());
        studentEntity.setFaculty(studentRequestDTO.getFaculty());
        return studentEntity;
    }
    public StudentResponseDTO toStudentResponseDTO(StudentEntity studentEntity){
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setFullName(studentEntity.getFullName());
        studentResponseDTO.setGender(studentEntity.getGender());
        studentResponseDTO.setHometown(studentEntity.getHomeTown());
        studentResponseDTO.setBirthday(studentEntity.getBirthday().toString());
        studentResponseDTO.setAverageMark(studentEntity.getAverageMark());
        studentResponseDTO.setClassName(studentEntity.getClassName());
        studentResponseDTO.setFaculty(studentEntity.getFaculty());
        return studentResponseDTO;
    }
    public BirthdayResponeDTO toBirthdayResponeDTO(StudentEntity studentEntity){
        BirthdayResponeDTO birthdayResponeDTO = new BirthdayResponeDTO();
        birthdayResponeDTO.setFullName(studentEntity.getFullName());
        birthdayResponeDTO.setClassName(studentEntity.getClassName());
        birthdayResponeDTO.setFaculty(studentEntity.getFaculty());
        birthdayResponeDTO.setBirthday(studentEntity.getBirthday());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        Date date = new Date();
        String[] currentDate = sdf.format(date).toString().split("-");
        String[] dateOfBirth = sdf.format(studentEntity.getBirthday()).toString().split("-");
        birthdayResponeDTO.setMessage("Today is your " +
                (Integer.parseInt(currentDate[0]) - Integer.parseInt(dateOfBirth[0])) +
                "th birthday. Happy Birthday " + studentEntity.getFullName());
        return birthdayResponeDTO;
    }
}

package com.dudv.student_management.validation;

import com.dudv.student_management.customException.DateException;
import com.dudv.student_management.customException.FieldRequiredException;
import com.dudv.student_management.customException.GradeException;
import com.dudv.student_management.customException.NameException;
import com.dudv.student_management.entity.StudentEntity;
import com.dudv.student_management.model.errorResponseModel.ErrorResponseDTO;
import com.dudv.student_management.model.requestModel.StudentRequestDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StudentValidation {
    public void validate(StudentRequestDTO studentRequestDTO){
        requestValidation(studentRequestDTO);
        gradeValidation(studentRequestDTO.getAverageMark());
        dateValidation(studentRequestDTO.getBirthday());
        nameValidation(studentRequestDTO.getFullName());
    }
    public void requestValidation(StudentRequestDTO studentRequestDTO){
        checkField(studentRequestDTO);
    }
    public void dateValidation(String date){
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        Date inputDate = null;
        try {
            inputDate = sdf.parse(date);
        } catch (ParseException e) {
            throw new DateException("Date exception");
        }
        if(inputDate.after(currentDate)) throw new DateException("Date exception");
        if(inputDate != null){
            String[] d1 = sdf.format(inputDate).toString().split("-");
            String[] d2 = date.split("-");
            for(int i = 0; i < d1.length; i++){
                if(Integer.parseInt(d1[i]) != Integer.parseInt(d2[i])) throw new DateException("Date exception");
            }
        }
    }
    public void gradeValidation(String grade){
        Double mark = null;
        try {
            mark = Double.parseDouble(grade);
        }
        catch (Exception e){
            throw new GradeException("Grade exception");
        }
        if(mark < 0.0 || mark > 10.0) throw new GradeException("Grade exception");
    }
    public void nameValidation(String name){
        if(name.length() > 50) throw new NameException("Name exception");
    }
    public void checkField(StudentRequestDTO studentRequestDTO){
        String message = "";
        if(checkNull(studentRequestDTO.getFullName())) message += "fullName:null ";
        if(checkNull(studentRequestDTO.getGender())) message += "gender:null ";
        if(checkNull(studentRequestDTO.getHometown())) message += "hometown:null ";
        if(checkNull(studentRequestDTO.getBirthday())) message += "birthday:null ";
        if(checkNull(studentRequestDTO.getFaculty())) message += "faculty:null ";
        if(checkNull(studentRequestDTO.getClassName())) message += "className:null ";
        if(studentRequestDTO.getAverageMark() == null) message += "averageMar:null ";
        if(
                checkNull(studentRequestDTO.getFullName())||
                checkNull(studentRequestDTO.getGender()) ||
                checkNull(studentRequestDTO.getHometown()) ||
                checkNull(studentRequestDTO.getBirthday()) ||
                checkNull(studentRequestDTO.getFaculty()) ||
                checkNull(studentRequestDTO.getClassName()) ||
                studentRequestDTO.getAverageMark() == null

        ) throw new FieldRequiredException("Field required exception!" + message);
    }
    public boolean checkNull(String s){
        if(s == null || s.trim().equals("")) return true;
        return false;
    }
}

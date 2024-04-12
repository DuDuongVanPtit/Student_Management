package com.dudv.student_management.repository;

import com.dudv.student_management.entity.StudentEntity;
import com.dudv.student_management.model.responseModel.BirthdayResponeDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface StudentRepository {
    String save(StudentEntity studentEntity);
    String deleteById(Long id);
    String update(Long id, StudentEntity studentEntity);
    List<StudentEntity> find(Map<String, Object> params);
    List<StudentEntity> getStudentBirthdayToday(String day, String month);
}

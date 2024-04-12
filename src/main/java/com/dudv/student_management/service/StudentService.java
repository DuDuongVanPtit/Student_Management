package com.dudv.student_management.service;

import com.dudv.student_management.model.requestModel.StudentRequestDTO;
import com.dudv.student_management.model.responseModel.BirthdayResponeDTO;
import com.dudv.student_management.model.responseModel.StudentResponseDTO;

import java.util.List;
import java.util.Map;

public interface StudentService {
    String save(StudentRequestDTO studentRequestDTO);
    String deleteById(Long id);
    String update(Long id, StudentRequestDTO studentRequestDTO);
    List<StudentResponseDTO> find(Map<String, Object> params);
    List<BirthdayResponeDTO> getStudentBirthdayToday();
}

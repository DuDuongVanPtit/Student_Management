package com.dudv.student_management.service.impl;

import com.dudv.student_management.converter.StudentConverter;
import com.dudv.student_management.entity.StudentEntity;
import com.dudv.student_management.model.errorResponseModel.ErrorResponseDTO;
import com.dudv.student_management.model.requestModel.StudentRequestDTO;
import com.dudv.student_management.model.responseModel.BirthdayResponeDTO;
import com.dudv.student_management.model.responseModel.StudentResponseDTO;
import com.dudv.student_management.repository.StudentRepository;
import com.dudv.student_management.service.StudentService;
import com.dudv.student_management.validation.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentConverter studentConverter;
    @Override
    public String save(StudentRequestDTO studentRequestDTO) {
        StudentEntity studentEntity = studentConverter.toStudentEntity(studentRequestDTO);
        return studentRepository.save(studentEntity);
    }
    @Override
    public String deleteById(Long id) {
        return studentRepository.deleteById(id);

    }
    @Override
    public String update(Long id, StudentRequestDTO studentRequestDTO) {
        StudentEntity studentEntity = studentConverter.toStudentEntity(studentRequestDTO);
        return studentRepository.update(id, studentEntity);
    }

    @Override
    public List<StudentResponseDTO> find(Map<String, Object> params) {
        List<StudentEntity> studentEntities = studentRepository.find(params);
        List<StudentResponseDTO> studentResponseDTOS = new ArrayList<>();
        for(StudentEntity s : studentEntities){
            studentResponseDTOS.add(studentConverter.toStudentResponseDTO(s));
        }
        return studentResponseDTOS;
    }

    @Override
    public List<BirthdayResponeDTO> getStudentBirthdayToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        Date date = new Date();
        String[] d = sdf.format(date).toString().split("-");
        List<StudentEntity> studentEntities = studentRepository.getStudentBirthdayToday(d[1], d[2]);
        List<BirthdayResponeDTO> birthdayResponeDTOS = new ArrayList<>();
        for(StudentEntity s : studentEntities){
            birthdayResponeDTOS.add(studentConverter.toBirthdayResponeDTO(s));
        }
        return birthdayResponeDTOS;
    }
}

package com.dudv.student_management.controler;

import com.dudv.student_management.entity.StudentEntity;
import com.dudv.student_management.model.requestModel.StudentRequestDTO;
import com.dudv.student_management.model.responseModel.BirthdayResponeDTO;
import com.dudv.student_management.model.responseModel.StudentResponseDTO;
import com.dudv.student_management.service.StudentService;
import com.dudv.student_management.validation.StudentValidation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentControler {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentValidation studentValidation;

    @PostMapping(value = "/student")
    public String addStudent(@RequestBody StudentRequestDTO studentRequestDTO){
        studentValidation.validate(studentRequestDTO);
        return studentService.save(studentRequestDTO);
    }
    @DeleteMapping(value = "/student/{id}")
    public String deleteStudent(@PathVariable Long id){
        return studentService.deleteById(id);
    }
    @PutMapping(value = "/student/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO studentRequestDTO){
        studentValidation.validate(studentRequestDTO);
        return studentService.update(id, studentRequestDTO);
    }
    @GetMapping(value = "/student")
    public List<StudentResponseDTO> getStudent(@RequestParam Map<String, Object> params){
        if(params.containsKey("birthdayFrom")) studentValidation.dateValidation(params.get("birthdayFrom").toString());
        if(params.containsKey("birthdayTo")) studentValidation.dateValidation(params.get("birthdayTo").toString());
        if(params.containsKey("averageMarkFrom")) studentValidation.gradeValidation(params.get("averageMarkFrom").toString());
        if(params.containsKey("averageMarkTo")) studentValidation.gradeValidation(params.get("averageMarkTo").toString());

        return studentService.find(params);
    }
    @GetMapping(value = "/student/birthday")
    public List<BirthdayResponeDTO> greeting(){
        List<BirthdayResponeDTO> studentResponseDTOS = studentService.getStudentBirthdayToday();
        return studentResponseDTOS;
    }
}
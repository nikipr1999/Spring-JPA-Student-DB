package com.example.restapi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
}

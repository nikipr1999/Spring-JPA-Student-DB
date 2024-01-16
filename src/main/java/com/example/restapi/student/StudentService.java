package com.example.restapi.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("This email is already taken.");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long id) {
        Boolean studentExists = studentRepository.existsById(id);
        if(studentExists){
            System.out.println(studentRepository.findById(id).get().getName() + " is deleted.");
            studentRepository.deleteById(id);
        }
        else {
            throw new IllegalStateException("This Student doesn't exists");
        }
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Boolean studentExists = studentRepository.existsById(id);
        if(studentExists){
            Student student = studentRepository.findById(id).get();
            if (name!= null && name.length()>0 && student.getName()!=name)
                student.setName(name);
            if (email!= null && email.length()>0 && student.getEmail()!=email ){
                if (studentRepository.findStudentByEmail(email).isPresent())
                    throw new IllegalStateException("This email is already taken.");
                student.setEmail(email);
            }
            System.out.println(student.getName() + " is updated.");
        }
        else {
            throw new IllegalStateException("This Student doesn't exists");
        }
    }
}

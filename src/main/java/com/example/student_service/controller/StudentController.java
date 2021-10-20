package com.example.student_service.controller;


import com.example.student_service.VO.ResponseTemplateVO;
import com.example.student_service.entity.Student;
import com.example.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
//@EnableEurekaClient
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String helloWorld(){
        return "Student service!";
    }

    @PostMapping
    public Student saveUser(@RequestBody Student student ){
        return studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getStudentWithDepartment(@PathVariable("id")
                                                                Long studentId){
        return studentService.getStudentWithDepartment(studentId);
    }

}

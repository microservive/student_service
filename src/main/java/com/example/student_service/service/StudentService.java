package com.example.student_service.service;


import com.example.student_service.VO.Khoa;
import com.example.student_service.VO.ResponseTemplateVO;
import com.example.student_service.entity.Student;
import com.example.student_service.repository.StudentReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {

    @Autowired
    private StudentReposity studentReposity;
    @Autowired
    private RestTemplate restTemplate;

    public Student saveStudent(Student student) {

        return studentReposity.save(student);
    }

    public ResponseTemplateVO getStudentWithDepartment(Long studentId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Student student = studentReposity.findById(studentId).get();
        vo.setStudent(student);
        Khoa khoa =
                restTemplate.getForObject("http://localhost:9001/khoa/"
                                + student.getKhoaId(),
                        Khoa.class);

        vo.setKhoa(khoa);

        return vo;
    }
}

package com.example.student_service.service;


import com.example.student_service.VO.Khoa;
import com.example.student_service.VO.ResponseTemplateVO;
import com.example.student_service.entity.Student;
import com.example.student_service.repository.StudentReposity;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {
    //tạo bean rest template
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
    @Autowired
    private StudentReposity studentReposity;
    @Autowired
    private RestTemplate restTemplate;

    @RateLimiter(name="basicExample")
    public Student saveStudent(Student student) {
        return studentReposity.save(student);
    }


    public void deleteStudent(long id) {
       studentReposity.deleteById(id);
    }
    @RateLimiter(name="basicExample")
    @Retry(name="basic")
    public ResponseTemplateVO getStudentWithDepartment(Long studentId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Student student = studentReposity.findById(studentId).get();
        vo.setStudent(student);
        Khoa khoa =
                restTemplate.getForObject("http://localhost:8001/khoa/"
                                + student.getKhoaId(),
                        Khoa.class);

        vo.setKhoa(khoa);

        return vo;
    }

}

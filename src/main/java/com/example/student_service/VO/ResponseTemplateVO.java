package com.example.student_service.VO;

import com.example.student_service.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseTemplateVO {
    private Student student;
    private Khoa khoa;
}

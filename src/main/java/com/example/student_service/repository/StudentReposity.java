package com.example.student_service.repository;

import com.example.student_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
 public interface StudentReposity extends JpaRepository<Student, Long> {


}

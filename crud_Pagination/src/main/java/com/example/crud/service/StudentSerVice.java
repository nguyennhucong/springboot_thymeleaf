package com.example.crud.service;

import com.example.crud.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentSerVice {
    List<Student> findAll();
    Student findById(Long id);
    Student saveStudent(Student student);
    Student updateStudent(Student student, Long id);

    void deleteStudent(Long id);

    Page<Student> findPaginated(int pageNo, int pageSize);
 }

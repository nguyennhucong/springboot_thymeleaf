package com.example.demo.webdemo.service;


import com.example.demo.webdemo.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudent();
    Student saveStudent(Student student);
    Student updateStudent(Student student);
    Student getStudentById(Long id);
    void deleteStudent(Long id);
}

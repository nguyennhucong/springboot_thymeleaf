package com.example.crud.service;

import com.example.crud.StudentException;
import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentSerVice{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll() ;
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new StudentException("Student", "ID", id));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        Student studentEx = studentRepository.findById(id).orElse(null);
        studentEx.setFirstName(student.getFirstName());
        studentEx.setLastName(student.getLastName());
        studentEx.setEmail(student.getEmail());
        studentRepository.save(studentEx);
        return studentEx;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.studentRepository.findAll(pageable);
    }
}

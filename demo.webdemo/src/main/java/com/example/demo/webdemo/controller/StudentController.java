package com.example.demo.webdemo.controller;

import com.example.demo.webdemo.entity.Student;
import com.example.demo.webdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {


    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudent(Model model){
        model.addAttribute("students", studentService.getAllStudent());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentFrom(Model model){
        // tao object de lay hoc sinh tu data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentFrom(@PathVariable Long id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model){

        //Lay hs tu db bang id
        Student exittingStudent = studentService.getStudentById(id);
        exittingStudent.setFirstName(student.getFirstName());
        exittingStudent.setLastName(student.getLastName());
        exittingStudent.setEmail(student.getEmail());

        //update
        studentService.updateStudent(exittingStudent);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}

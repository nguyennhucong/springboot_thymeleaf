package com.example.crud.controller;

import com.example.crud.model.Student;
import com.example.crud.service.StudentSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Studentcontroller {
    @Autowired
    private StudentSerVice studentSerVice;

//    @GetMapping("/students")
//    public String listStudent(Model model){
//        model.addAttribute("students", studentSerVice.findAll());
//        return "students";
//    }

    @GetMapping("/students")
    public String listStudent(Model model){
        return findPaginated(1, model);
    }

    @GetMapping("/students/new")
    public String createStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("students") Student student){
        studentSerVice.saveStudent(student);
        return "redirect:students";
    }

    @GetMapping("students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        model.addAttribute(studentSerVice.findById(id));
        return "edit_student";
    }

    @PostMapping("students/{id}")
    public String updateStudent(@ModelAttribute("student") Student student, @PathVariable Long id){
        studentSerVice.updateStudent(student, id);
        return "redirect:/students";
    }

    @GetMapping("students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentSerVice.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/student/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<Student> page = studentSerVice.findPaginated(pageNo, pageSize);
        List<Student> listStudent = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("students", listStudent);
        return "students";
    }
}

package com.studentservice.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentservice.entities.Student;
import com.studentservice.services.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PostMapping
    public Mono<Student> createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteStudent(@PathVariable Long id) {
        return service.deleteStudent(id);
    }

    @GetMapping("/{id}/attendance")
    public Flux<?> getStudentAttendance(@PathVariable Long id) {
        System.out.println(service.getStudentAttendance(id));
        return service.getStudentAttendance(id);
    }

}

package com.studentservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.studentservice.entities.Student;
import com.studentservice.repositories.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final WebClient.Builder webClientBuilder;

    public StudentService(StudentRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.webClientBuilder = webClientBuilder;
    }

    public Flux<Student> getAllStudents() {
        return repository.findAll();
    }

    public Mono<Student> getStudentById(Long id) {
        return repository.findById(id);
    }

    public Mono<Student> createStudent(Student student) {
        return repository.save(student);
    }

    public Mono<Void> deleteStudent(Long id) {
        return repository.deleteById(id);
    }
    public Flux<?> getStudentAttendance(Long studentId) {
        return webClientBuilder.build()
            .get()
            // .uri("http://attendance-service/attendance/student/{studentId}", studentId)
            .uri("http://localhost:8085/attendance/student/{studentId}", studentId)
            .retrieve()
            .bodyToFlux(Object.class);
    }

}

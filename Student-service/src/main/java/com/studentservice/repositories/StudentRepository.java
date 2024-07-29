package com.studentservice.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.studentservice.entities.Student;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

}

package com.attendanceservice.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.attendanceservice.entities.Attendance;

import reactor.core.publisher.Flux;

public interface AttendanceRepo extends ReactiveCrudRepository<Attendance, Long> {
    Flux<Attendance> findByStudentId(Long studentId);

}

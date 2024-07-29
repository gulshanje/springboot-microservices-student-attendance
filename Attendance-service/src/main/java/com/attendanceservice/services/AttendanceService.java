package com.attendanceservice.services;

import org.springframework.stereotype.Service;

import com.attendanceservice.entities.Attendance;
import com.attendanceservice.repositories.AttendanceRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class AttendanceService {
    private final AttendanceRepo attendanceRepo;

    public AttendanceService(AttendanceRepo attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }

     public Flux<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }

    public Flux<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepo.findByStudentId(studentId);
    }

    public Mono<Attendance> createAttendance(Attendance attendance) {
        return attendanceRepo.save(attendance);
    }

    public Mono<Void> deleteAttendance(Long id) {
        return attendanceRepo.deleteById(id);
    }
}

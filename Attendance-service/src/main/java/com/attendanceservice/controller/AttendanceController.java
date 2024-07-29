package com.attendanceservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendanceservice.entities.Attendance;
import com.attendanceservice.services.AttendanceService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public Flux<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/student/{studentId}")
    public Flux<Attendance> getAttendanceByStudentId(@PathVariable Long studentId) {
        return attendanceService.getAttendanceByStudentId(studentId);
    }

    @PostMapping
    public Mono<Attendance> createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.createAttendance(attendance);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAttendance(@PathVariable Long id) {
        return attendanceService.deleteAttendance(id);
    }
}

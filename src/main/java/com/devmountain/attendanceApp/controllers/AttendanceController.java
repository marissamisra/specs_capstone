package com.devmountain.attendanceApp.controllers;

import com.devmountain.attendanceApp.dtos.AttendanceDto;
import com.devmountain.attendanceApp.services.AttendanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceServiceImpl attendanceService;

    @Autowired
    public AttendanceController(AttendanceServiceImpl attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendance(@PathVariable Long id) {
        AttendanceDto attendance = attendanceService.getAttendanceById(id);
        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attendance);
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> createAttendance(@RequestBody AttendanceDto attendanceDto) {
        AttendanceDto createdAttendance = attendanceService.createAttendance((attendanceDto));
        return ResponseEntity.ok(createdAttendance);
    }
}

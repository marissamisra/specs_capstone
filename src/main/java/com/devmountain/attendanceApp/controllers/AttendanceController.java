package com.devmountain.attendanceApp.controllers;

import com.devmountain.attendanceApp.dtos.AttendanceDto;
import com.devmountain.attendanceApp.services.AttendanceServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceServiceImpl attendanceService;

    @Autowired
    public AttendanceController(AttendanceServiceImpl attendanceService) {
        this.attendanceService = attendanceService;
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendance(@PathVariable Long id) {
        AttendanceDto attendance = attendanceService.getAttendanceById(id);
        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attendance);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @GetMapping("/student/{userId}")
    public ResponseEntity<List<AttendanceDto>> getAttendanceForStudent(@PathVariable Long userId) {
        List<AttendanceDto> attendanceRecords = attendanceService.getAttendanceByUserId(userId);
        if (attendanceRecords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(attendanceRecords);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AttendanceDto>> getAllAttendanceForCourse(@PathVariable Long courseId) {
        List<AttendanceDto> attendance = attendanceService.findAllAttendanceForCourse(courseId);
        return ResponseEntity.ok(attendance);
    }

    @CrossOrigin(origins = {"http://localhost:3000/", "http://127.0.0.1:50394"})
    @PostMapping
    public ResponseEntity<AttendanceDto> createAttendance(@RequestBody AttendanceDto attendanceDto) {
        AttendanceDto createdAttendance = attendanceService.createAttendance((attendanceDto));
        return ResponseEntity.ok(createdAttendance);
    }
}

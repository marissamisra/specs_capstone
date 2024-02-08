package com.devmountain.attendanceApp.services;

import com.devmountain.attendanceApp.dtos.AttendanceDto;
import com.devmountain.attendanceApp.entities.Attendance;
import com.devmountain.attendanceApp.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceServiceImpl {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public AttendanceDto getAttendanceById(Long id) {
        Optional<Attendance> attendanceOptional = attendanceRepository.findById(id);
        if (attendanceOptional.isPresent()) {
            return new AttendanceDto(attendanceOptional.get());
        } else {
            return null;
        }
    }
}

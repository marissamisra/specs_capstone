package com.devmountain.attendanceApp.services;

import com.devmountain.attendanceApp.dtos.AttendanceDto;
import com.devmountain.attendanceApp.entities.Attendance;
import com.devmountain.attendanceApp.entities.User;
import com.devmountain.attendanceApp.repositories.AttendanceRepository;
import com.devmountain.attendanceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttendanceServiceImpl {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    public AttendanceDto getAttendanceById(Long id) {
        Optional<Attendance> attendanceOptional = attendanceRepository.findById(id);
        if (attendanceOptional.isPresent()) {
            return new AttendanceDto(attendanceOptional.get());
        } else {
            return null;
        }
    }

    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
        User user = userRepository.findById(attendanceDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Attendance attendance = new Attendance();
        attendance.setDate(attendanceDto.getDate());
        attendance.setPresent(attendanceDto.isPresent());
        attendance.setTardy(attendanceDto.isTardy());
        attendance.setUser(user);

        Attendance savedAttendance = attendanceRepository.save(attendance);
        attendanceDto.setId(savedAttendance.getId());
        attendanceDto.setUserId(user.getId());
        return attendanceDto;

    }
}

package com.devmountain.attendanceApp.repositories;

import com.devmountain.attendanceApp.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByCourseId(Long courseId);
    List<Attendance> findByUserId(Long userId);
}

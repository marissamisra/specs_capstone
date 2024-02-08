package com.devmountain.attendanceApp.dtos;

import com.devmountain.attendanceApp.entities.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto implements Serializable {
    private Long id;
    private int studentId;
    private boolean present;
    private boolean tardy;
    private LocalDate date;
    private User user;

    public AttendanceDto(Attendance attendance){
        if (attendance.getId() != null){
            this.id = attendance.getId();

        }
        if (attendance.getStudentId() != 0){
            this.studentId = attendance.getStudentId();

        }
        if (attendance.getDate() != null){
            this.date = attendance.getDate();

        }
        if (attendance.getUser() != null){
            this.user = attendance.getUser();

        }
        this.present = attendance.isPresent();
        this.tardy = attendance.isTardy();
    }

}

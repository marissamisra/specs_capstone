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
    private boolean present;
    private boolean tardy;
    private LocalDate date;
    private Long userId;
//    private Courses course;

    public AttendanceDto(Attendance attendance){
        if (attendance.getId() != null){
            this.id = attendance.getId();

        }

        if (attendance.getDate() != null){
            this.date = attendance.getDate();

        }
        if (attendance.getUser() != null){
            this.userId = attendance.getUser().getId();

        }
        this.present = attendance.isPresent();
        this.tardy = attendance.isTardy();
    }

}

package com.devmountain.attendanceApp.entities;

import com.devmountain.attendanceApp.dtos.AttendanceDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int studentId;

    @Column
    private boolean present;

    @Column
    private boolean tardy;

    @Column
    private LocalDate date;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Attendance (AttendanceDto attendanceDto){

        if (attendanceDto.getId() != 0){
            this.id = attendanceDto.getId();

        }

        if (attendanceDto.getDate() != null){
            this.date = attendanceDto.getDate();

        }
        this.present = attendanceDto.isPresent();
        this.tardy = attendanceDto.isTardy();
    }
}

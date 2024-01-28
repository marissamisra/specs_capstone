package com.devmountain.attendanceApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private int student_id;

    @Column
    private boolean present;

    @Column
    private boolean tardy;

    @Column
    private LocalDate date;

    @ManyToOne
    @JsonBackReference
    private User user;
}

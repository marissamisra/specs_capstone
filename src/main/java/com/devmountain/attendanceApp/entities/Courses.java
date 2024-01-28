package com.devmountain.attendanceApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int user_id;

    @ManyToMany
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private Notes note;
}

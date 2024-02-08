package com.devmountain.attendanceApp.entities;

import com.devmountain.attendanceApp.dtos.UserDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private boolean instructor;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<Notes> notesSet = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<Attendance> attendanceSet = new HashSet<>();

    //@ManyToMany(mappedBy = "coursesSet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(mappedBy = "userSet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Courses> coursesSet = new HashSet<>();

    public User (UserDto userDto){

        if (userDto.getId() != 0){
            this.id = userDto.getId();
        }
        if (userDto.getName() != null){
            this.name = userDto.getName();
        }
        this.instructor = userDto.isInstructor();
    }
}


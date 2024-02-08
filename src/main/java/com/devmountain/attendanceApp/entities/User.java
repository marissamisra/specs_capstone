package com.devmountain.attendanceApp.entities;

import com.devmountain.attendanceApp.dtos.UserDto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference(value = "user-notes")
    private Set<Notes> notesSet = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference(value = "user-attendance")
    private Set<Attendance> attendanceSet = new HashSet<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-course")
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

    public void addCourse(Courses course) {
        this.coursesSet.add(course);
        course.getUsers().add(this);
    }

    public void removeCourse(Courses course) {
        this.coursesSet.remove(course);
        course.getUsers().remove(this);
    }
}


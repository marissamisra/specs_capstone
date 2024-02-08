package com.devmountain.attendanceApp.entities;

import com.devmountain.attendanceApp.dtos.CoursesDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<User> userSet = new HashSet<>();

    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private Set<Notes> noteSet;

    public Courses (CoursesDto coursesDto){
        if (coursesDto.getId() != null){
            this.id = coursesDto.getId();

        }
        if (coursesDto.getUserSet() != null){
            this.userSet = coursesDto.getUserSet();

        }
        if (coursesDto.getNoteSet() != null){
            this.noteSet = coursesDto.getNoteSet();

        }
    }
    public void addUser(User user) {
        this.userSet.add(user);
        user.getCoursesSet().add(this); // Also add this course to the user's coursesSet
    }

    public void removeUser(User user) {
        this.userSet.remove(user);
        user.getCoursesSet().remove(this); // Also remove this course from the user's coursesSet
    }
}

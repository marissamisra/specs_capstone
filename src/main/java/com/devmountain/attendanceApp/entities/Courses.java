package com.devmountain.attendanceApp.entities;

import com.devmountain.attendanceApp.dtos.CoursesDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
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

    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_user",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonManagedReference(value = "user-course")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference(value = "course-notes")
    private Set<Notes> noteSet = new HashSet<>();

    @Override
    public boolean equals(Object object) {
        if(this == object) return true;
        if(!(object instanceof Courses)) return false;
        Courses courses = (Courses) object;
        return Objects.equals(id, courses.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Courses (CoursesDto coursesDto){
        if (coursesDto.getId() != null){
            this.id = coursesDto.getId();
        }
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getCoursesSet().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getCoursesSet().remove(this);
    }
}

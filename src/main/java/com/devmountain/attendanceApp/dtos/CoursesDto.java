package com.devmountain.attendanceApp.dtos;

import com.devmountain.attendanceApp.entities.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDto implements Serializable {

    private Long id;
    private String name;
    private Set<UserDto> enrolledUsers;

    public void setUserSet(Set<UserDto> userSet) {
        this.enrolledUsers = userSet;
    }

    public CoursesDto(Courses course){
        this.id = course.getId();
        this.name = course.getName();
        this.enrolledUsers = course.getUsers().stream().map(UserDto::new).collect(Collectors.toSet());
    }
}

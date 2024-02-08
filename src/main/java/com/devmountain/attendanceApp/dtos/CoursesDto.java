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
    private Set<UserDto> usersDto;
    private Set<NotesDto> noteDtos;

    public void setUserSet(Set<UserDto> userSet) {
        this.usersDto = userSet;
    }

    public void setNoteSet(Set<NotesDto> noteSet) {
        this.noteDtos = noteSet;
    }

    public CoursesDto(Courses course){
        this.id = course.getId();
        this.name = course.getName();
        if (course.getUsers() != null){
            this.usersDto = course.getUsers().stream().map(UserDto::new).collect(Collectors.toSet());
        }
        if (course.getNoteSet() != null){
            this.noteDtos = course.getNoteSet().stream().map(NotesDto::new).collect(Collectors.toSet());
        }

    }

}

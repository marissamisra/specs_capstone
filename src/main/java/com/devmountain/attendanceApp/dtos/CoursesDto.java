package com.devmountain.attendanceApp.dtos;

import com.devmountain.attendanceApp.entities.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDto implements Serializable {

    private Long id;
    private String name;
    private Set<UserDto> userDtos;
    private Set<NotesDto> noteDtos;

    public void setUserSet(Set<UserDto> userSet) {
        this.userDtos = userSet;
    }

    public void setNoteSet(Set<NotesDto> noteSet) {
        this.noteDtos = noteSet;
    }

    public CoursesDto(Courses course){
        if (course.getId() != null){
            this.id = course.getId();
        }
//        if (course.getUsers() != null){
//            this.userDtos = course.getUsers();
//        }
//
//        if (course.getNoteSet() != null){
//            this.noteDtos = course.getNoteSet();
//        }

    }

}

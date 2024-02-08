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
    private Set<User> userSet;
    private Set<Notes> noteSet;

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public void setNoteSet(Set<Notes> noteSet) {
        this.noteSet = noteSet;
    }

    public CoursesDto(Courses courses){
        if (courses.getId() != null){
            this.id = courses.getId();

        }
        if (courses.getUserSet() != null){
            this.userSet = courses.getUserSet();

        }

        if (courses.getNoteSet() != null){
            this.noteSet = courses.getNoteSet();

        }

    }

}

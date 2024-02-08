package com.devmountain.attendanceApp.dtos;

import com.devmountain.attendanceApp.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto implements Serializable {
    private Long id;
    private int studentId;
    private String note;
    private User user;
    private Courses course;

    public NotesDto(Notes note){
        if (note.getId() != null){
            this.id = note.getId();

        }
        if (note.getStudentId() != 0){
            this.studentId = note.getStudentId();

        }
        if (note.getNote() != null){
            this.note = note.getNote();

        }
        if (note.getUser() != null){
            this.user = note.getUser();

        }
        if (note.getCourse() != null){
            this.course = note.getCourse();

        }
    }
}

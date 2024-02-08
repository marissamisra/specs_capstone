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
    private String note;
    private Long userId;
    private Long courseId;

    public NotesDto(Notes note){
        this.id = note.getId();
        this.note = note.getNote();
        this.userId = note.getUser() != null ? note.getUser().getId() : null;
        this.courseId = note.getCourse() != null ? note.getCourse().getId() : null;

    }
}

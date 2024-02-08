package com.devmountain.attendanceApp.entities;

import com.devmountain.attendanceApp.dtos.NotesDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int studentId;

    @Column(columnDefinition = "text")
    private String note;

    //@Column
    //private int courseId;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Courses course;

    public Notes (NotesDto notesDto){
        if (notesDto.getId() != 0){
            this.id = notesDto.getId();
        }
        if (notesDto.getStudentId() != 0){
            this.studentId = notesDto.getStudentId();

        }
        if (notesDto.getNote() != null){
            this.note = notesDto.getNote();

        }
        if (notesDto.getUser() != null){
            this.user = notesDto.getUser();

        }
        if (notesDto.getCourse() != null){
            this.course = notesDto.getCourse();

        }
    }
}

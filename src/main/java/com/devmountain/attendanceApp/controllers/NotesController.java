package com.devmountain.attendanceApp.controllers;

import com.devmountain.attendanceApp.dtos.NotesDto;
import com.devmountain.attendanceApp.services.NotesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private final NotesServiceImpl notesService;

    @Autowired
    public NotesController(NotesServiceImpl notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotesDto> getNotes(@PathVariable Long id) {
        NotesDto notes = notesService.getNotesById(id);
        if (notes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public String createNote(@RequestBody NotesDto notesDto) {
        return "Note created successfully";
    }
}

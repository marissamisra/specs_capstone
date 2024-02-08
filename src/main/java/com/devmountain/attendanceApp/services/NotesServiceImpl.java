package com.devmountain.attendanceApp.services;

import com.devmountain.attendanceApp.dtos.NotesDto;
import com.devmountain.attendanceApp.entities.Notes;
import com.devmountain.attendanceApp.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotesServiceImpl {

    @Autowired
    private NotesRepository notesRepository;

    public NotesDto getNotesById(Long id) {
        Optional<Notes> notesOptional = notesRepository.findById(id);
        if (notesOptional.isPresent()) {
            return new NotesDto(notesOptional.get());
        } else {
            return null;
        }
    }
}
